import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ComputerGuessesGameTest {

    // TODO: more cases

    @Test
    void numGuesses() {
        // TODO: should numguesses be like this?
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame();
        for (int i = 0; i < 5; i++) {
            computerGuessesGame.guessHigher();
            computerGuessesGame.guessLower();
        }
        assertEquals(9, computerGuessesGame.getNumGuesses());
    }

    // using dependency injection
    @Test
    void guessLower() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(50, 60, 40);
        computerGuessesGame.guessLower();
        assertEquals(45, computerGuessesGame.getLastGuess());

        // TODO: could this ever happen?
        computerGuessesGame = new ComputerGuessesGame(50, 50, 49);
        computerGuessesGame.guessLower();
        assertEquals(49, computerGuessesGame.getLastGuess());
    }

    // using dependency injection
    @Test
    void guessHigher() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(50, 60, 40);
        computerGuessesGame.guessHigher();
        assertEquals(56, computerGuessesGame.getLastGuess());

        // TODO: could this ever happen?
        computerGuessesGame = new ComputerGuessesGame(49, 50, 49);
        computerGuessesGame.guessHigher();
        assertEquals(50, computerGuessesGame.getLastGuess());
    }

    // TODO: I really don't think I need to check too many corner cases for this...
    // using dependency injection
    @Test
    void calculateNextGuessTest() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(-1, 60, 40);
        assertEquals(50, computerGuessesGame.getLastGuess());

        computerGuessesGame = new ComputerGuessesGame(60, 60);
        assertEquals(60, computerGuessesGame.getLastGuess());
    }
}
