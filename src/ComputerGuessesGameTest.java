import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ComputerGuessesGameTest {

    @Test
    void numGuesses() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame();
        for (int i = 0; i < 5; i++) {
            computerGuessesGame.guessHigher();
            computerGuessesGame.guessLower();
        }
        assertEquals(10, computerGuessesGame.getNumGuesses());
    }

    //using dependency injection
    @Test
    void guessLower1() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(50, 60, 40);
        computerGuessesGame.guessLower();
        assertEquals(45, computerGuessesGame.getLastGuess());
    }

    //using dependency injection
    @Test
    void guessLower2() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(50, 50, 49);
        computerGuessesGame.guessLower();
        assertEquals(49, computerGuessesGame.getLastGuess());
    }

    //using dependency injection
    @Test
    void guessHigher1() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(50, 60, 40);
        computerGuessesGame.guessHigher();
        assertEquals(56, computerGuessesGame.getLastGuess());
    }

    //using dependency injection
    @Test
    void guessHigher2() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(49, 50, 49);
        computerGuessesGame.guessHigher();
        assertEquals(50, computerGuessesGame.getLastGuess());
    }

    //using dependency injection
    @Test
    void calculateNextGuessTest() {
        ComputerGuessesGame computerGuessesGame = new ComputerGuessesGame(60, 40);
        assertEquals(50, computerGuessesGame.getLastGuess());

        computerGuessesGame = new ComputerGuessesGame(60, 60);
        assertEquals(60, computerGuessesGame.getLastGuess());
    }
}
