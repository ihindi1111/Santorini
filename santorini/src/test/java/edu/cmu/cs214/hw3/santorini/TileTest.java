package edu.cmu.cs214.hw3.santorini;

import org.junit.jupiter.api.Test;

import edu.cmu.cs214.hw3.santorini.backend.Tile;
import edu.cmu.cs214.hw3.santorini.backend.Worker;

import org.junit.Before;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    private Tile tile;

    @Before
    public void setUp() {
        tile = new Tile(0, 0);
    }

    @Test
    public void testInitialLevel() {
        assertEquals(0, tile.getLevel());
    }

    @Test
    public void testIncreaseLevel() {
        tile.increaseLevel();
        assertEquals(1, tile.getLevel());
    }

    @Test
    public void testIncreaseLevelUpToDome() {
        for (int i = 0; i < 4; i++) {
            tile.increaseLevel();
        }
        assertTrue(tile.hasDome());
        assertEquals(3, tile.getLevel());
    }

    @Test
    public void testOccupiedStatus() {
        tile.setOccupied(true);
        assertTrue(tile.isOccupied());
    }

    @Test
    public void testNotOccupiedStatus() {
        tile.setOccupied(false);
        assertFalse(tile.isOccupied());
    }

    @Test
    public void testBuildIncreasesLevel() {
        tile.build();
        assertEquals(1, tile.getLevel());
    }

    @Test
    public void testMoveWorkerChangesOccupancy() {
        Worker worker = new Worker(0, 1); // Assuming Worker constructor Worker(int x, int y)
        Tile workerTile = new Tile(0, 1);
        workerTile.setOccupied(true);

        tile.moveWorker(worker, workerTile);

        assertEquals(tile.getX(), worker.getX());
        assertEquals(tile.getY(), worker.getY());
        assertTrue(tile.isOccupied());
        assertFalse(workerTile.isOccupied());
    }
}
