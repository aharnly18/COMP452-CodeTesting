/**
 * An interface for a data structure, DB API, file reader, etc. that
 * tells us how many games were played that took some number of guesses
 * (e.g., How many games took 8 guesses? --> 17)
 *
 * You can edit this file, but the two abstract methods listed below must remain
 */
public abstract class GameStats {
    /**
     * @return the number of games played that took numGuesses
     */
    public abstract int numGames(int numGuesses);

    /**
     * @return the maximum number of guesses that any game took
     */
    public abstract int maxNumGuesses();

    public int numGamesInRange(int lowerBound, int upperBound) {
        int numGames = 0;

        for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
            numGames += numGames(numGuesses);
        }

        return numGames;
    }

    public int[] numGamesInBins(int[] binEdges) {
        int[] gamesInBins = new int[binEdges.length];
        for(int binIndex=0; binIndex<binEdges.length; binIndex++){
            final int lowerBound = binEdges[binIndex];
            final int upperBound = (binIndex == binEdges.length-1) ?
                    maxNumGuesses()-1 :
                    binEdges[binIndex+1];
            gamesInBins[binIndex] = numGamesInRange(lowerBound, upperBound);
        }
        return gamesInBins;
    }

}
