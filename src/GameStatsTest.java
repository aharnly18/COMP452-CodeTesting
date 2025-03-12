import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class GameStatsTest {

    @Test
    void numGamesInRangeEmptyMap() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        StatsFile statsFile = new StatsFile(map);
        assertEquals(0, statsFile.numGamesInRange(1,4));
    }

    @Test
    void numGamesInRangeWrongBounds() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,4);
        map.put(2,4);
        map.put(3,4);
        StatsFile statsFile = new StatsFile(map);
        assertEquals(0, statsFile.numGamesInRange(2,1));
    }

    @Test
    void numGamesInRangeBoundOf1() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,3);
        map.put(2,4);
        map.put(3,5);
        StatsFile statsFile = new StatsFile(map);
        assertEquals(4, statsFile.numGamesInRange(2,2));
    }

    @Test
    void numGamesInRangeGeneral() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,1);
        map.put(2,4);
        map.put(3,5);
        map.put(4,15);
        StatsFile statsFile = new StatsFile(map);
        assertEquals(9, statsFile.numGamesInRange(2,3));
    }

    @Test
    void numGamesInBins() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,1);
        map.put(2,4);
        map.put(3,5);
        map.put(4,15);
        int[] binEdges = {1, 2, 3, 4};
        int[] expected = {1, 4, 5, 15};
        StatsFile statsFile = new StatsFile(map);
        System.out.println(Arrays.toString(statsFile.numGamesInBins(binEdges)));
        assertArrayEquals(expected, statsFile.numGamesInBins(binEdges));
    }

    @Test
    void numGamesInBins2() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,1);
        map.put(2,4);
        map.put(3,5);
        map.put(4,15);
        int[] binEdges = {1, 3, 6};
        int[] expected = {5, 20, 0};
        StatsFile statsFile = new StatsFile(map);
        System.out.println(Arrays.toString(statsFile.numGamesInBins(binEdges)));
        assertArrayEquals(expected, statsFile.numGamesInBins(binEdges));
    }

    @Test
    void numGamesInBinsWrongEdges() {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,1);
        map.put(2,4);
        map.put(3,5);
        map.put(4,15);
        int[] binEdges = {3, 6, 1};
        int[] expected = {0, 0, 0};
        StatsFile statsFile = new StatsFile(map);
        System.out.println(Arrays.toString(statsFile.numGamesInBins(binEdges)));
        assertArrayEquals(expected, statsFile.numGamesInBins(binEdges));
    }
}
