import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * File-backed implementation of GameStats
 *
 * Returns the number of games *within the last 30 days* where the person took a given number of guesses
 */
public class StatsFile extends GameStats {
    public static final String FILENAME = "guess-the-number-stats.csv";

    // maps the number of guesses required to the number of games within
    // the past 30 days where the person took that many guesses
    private SortedMap<Integer, Integer> statsMap;

    public StatsFile(LocalDateTime limit, CSVReader csvReader) {
        statsMap = new TreeMap<>();
        try {
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                // values should have the date and the number of guesses as the two fields
                try {
                    GameResult result = GameResult.fromCSVRecord(record);
                    if (result.timestamp.isAfter(limit)) { // TODO: be able to test this???
                        statsMap.put(result.numGuesses, 1 + statsMap.getOrDefault(result.numGuesses, 0));
                    }
                } catch (NumberFormatException nfe) {
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw nfe;
                } catch (DateTimeParseException dtpe) {
                    // NOTE: In a full implementation, we would log this error and possibly alert the user
                    throw dtpe;
                }
            }
        } catch (CsvValidationException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }

    public StatsFile(SortedMap<Integer, Integer> map) {
        statsMap = map;
    }

    @Override
    public int numGames(int numGuesses) {
        return statsMap.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses(){
        return (statsMap.isEmpty() ? 0 : statsMap.lastKey());
    }

    public static void append(GameResult gameResult) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(StatsFile.FILENAME, true))) {
            writer.writeNext(gameResult.toCSVRecord());
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }

    public static StatsFile load() {
        try {
            return new StatsFile(
                LocalDateTime.now().minusDays(30),
                new CSVReader(new FileReader(FILENAME))
            );
        } catch(IOException e) {
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            return null;
        }
    }
}
