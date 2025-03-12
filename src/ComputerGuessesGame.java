public class ComputerGuessesGame {

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesGame(){
        this (-1, 1000, 1);
        calculateNextGuess();
    }

    public ComputerGuessesGame(int upperBound, int lowerBound){
        this (-1, upperBound, lowerBound);
        calculateNextGuess();
    }

    public ComputerGuessesGame(int lastGuess, int upperBound, int lowerBound){
        numGuesses = -1;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.lastGuess = lastGuess;
    }

    public void guessLower(){
        upperBound = Math.min(upperBound, lastGuess);
        calculateNextGuess();
    }

    public void guessHigher() {
        lowerBound = Math.max(lowerBound, lastGuess + 1);
        calculateNextGuess();
    }

    private void calculateNextGuess() {
        lastGuess = (lowerBound + upperBound + 1) / 2;
        numGuesses += 1;
    }

    public int getLastGuess() {
        return lastGuess;
    }

    public int getNumGuesses() {
        return numGuesses;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
