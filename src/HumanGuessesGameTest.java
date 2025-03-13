import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HumanGuessesGameTest {

    // TODO: should I test for guesses outside of the bounds??

    // should I test the constructor??
    @Test
    void constructor() {
        HumanGuessesGame humanGuessesGame = new HumanGuessesGame();

        assertEquals(0, humanGuessesGame.getNumGuesses());
        assertFalse(humanGuessesGame.isDone());
    }

    // tests for makeGuess

    //using dependency injection
    @Test
    void makeCorrectGuess() {
        HumanGuessesGame humanGuessesGame = new HumanGuessesGame(10);

        assertEquals(GuessResult.CORRECT, humanGuessesGame.makeGuess(10));
        assertTrue(humanGuessesGame.isDone());
    }

    //using dependency injection
    @Test
    void makeLowGuess() {
        HumanGuessesGame humanGuessesGame = new HumanGuessesGame(10);

        assertEquals(GuessResult.LOW, humanGuessesGame.makeGuess(5));
        assertFalse(humanGuessesGame.isDone());
    }

    //using dependency injection
    @Test
    void makeHighGuess() {
        HumanGuessesGame humanGuessesGame = new HumanGuessesGame(10);

        assertEquals(GuessResult.HIGH, humanGuessesGame.makeGuess(15));
        assertFalse(humanGuessesGame.isDone());
    }

    @Test
    void numGuess() {
        HumanGuessesGame humanGuessesGame = new HumanGuessesGame();

        assertEquals(0, humanGuessesGame.getNumGuesses());
        for (int i = 0; i < 5; i++) {
            humanGuessesGame.makeGuess(1);
        }
        assertEquals(5, humanGuessesGame.getNumGuesses());
    }


}
