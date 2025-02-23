import java.time.LocalDateTime;

/**
 * Data class to hold the result of a game
 * NOTE: You can refactor and edit this file if needed
 */
public class GameResult {
    public final LocalDateTime timestamp;
    public final boolean humanWasPlaying;
    public final int correctValue;
    public final int numGuesses;

    public GameResult(LocalDateTime timestamp, boolean humanWasPlaying, int correctValue, int numGuesses){
        this.timestamp = timestamp;
        this.humanWasPlaying = humanWasPlaying;
        this.correctValue = correctValue;
        this.numGuesses = numGuesses;
    }

    public GameResult(boolean humanWasPlaying, int correctValue, int numGuesses) {
        this(LocalDateTime.now(), humanWasPlaying, correctValue, numGuesses);
    }

    public String[] toCSVRecord() {
        String[] record = new String[2];
        record[0] = timestamp.toString();
        record[1] = Integer.toString(numGuesses);
        return record;
    }

    public static GameResult fromCSVRecord(String[] record){
        LocalDateTime timestamp = LocalDateTime.parse(record[0]);
        int numGuesses = Integer.parseInt(record[1]);
        return new GameResult(timestamp, true, 0, numGuesses);
    }
}
