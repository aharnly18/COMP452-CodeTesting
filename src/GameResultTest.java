import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class GameResultTest {

    // test for toCSVRecord
    @Test
    void csvRecordTest() {
        String[] record = {"2020-02-24T20:10:29.110278500", "4"};
        GameResult gameResult = new GameResult(LocalDateTime.parse("2020-02-24T20:10:29.110278500"), true, 10, 10);
        assertEquals(record, gameResult.toCSVRecord());
    }

    // tests for fromCSVRecord
    @Test
    void fromCSVNumberFormatException() {
        String[] record = {"2020-02-24T20:10:29.110278500", "Hi!"};
        assertThrows(NumberFormatException.class, () -> GameResult.fromCSVRecord(record));
    }

    @Test
    void fromCSVNumberFormatExceptionDouble() {
        String[] record = {"2020-02-24T20:10:29.110278500", "4.7"};
        assertThrows(NumberFormatException.class, () -> GameResult.fromCSVRecord(record));
    }

    @Test
    void fromCSVDateTimeParseException1() {
        String[] record = {"Hi!", "4"};
        assertThrows(DateTimeParseException.class, () -> GameResult.fromCSVRecord(record));
    }

    @Test
    void fromCSVDateTimeParseException2() {
        String[] record = {"2020-2-24T20:10:29.110278500", "4"};
        assertThrows(DateTimeParseException.class, () -> GameResult.fromCSVRecord(record));
    }

    @Test
    void fromCSVNormal() {
        String[] record = {"2020-02-24T20:10:29.110278500", "4"};
        GameResult gameResult = GameResult.fromCSVRecord(record);
        assertEquals("2020-02-24T20:10:29.110278500", gameResult.getTimestamp().toString());
        assertEquals(4, gameResult.getNumGuesses());

    }

    //  TODO: should i test wrong numbers, like -1?

}
