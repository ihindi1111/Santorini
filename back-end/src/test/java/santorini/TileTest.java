import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import components.Tile;
import components.Worker;

public class TileTest {
    private Tile tile;
    private Worker worker;

    @Before
    public void setUp() {
        tile = new Tile(0, 0);
        worker = new Worker(1, 0, 0); // Assuming Worker constructor accepts playerID, x, y
    }

    @Test
    public void testInitialConditions() {
        assertFalse("Newly created tile should not have a dome", tile.hasDome());
        assertEquals("Newly created tile should have level 0", 0, tile.getLevel());
        assertNull("Newly created tile should not have a worker", tile.getWorker());
    }

    @Test
    public void testSetWorker() {
        tile.setWorker(worker);
        assertTrue("Tile should be occupied after setting worker", tile.isOccupied());
        assertEquals("Tile should return the correct worker", worker, tile.getWorker());
    }

    @Test
    public void testBuildIncreasesLevel() {
        tile.build();
        assertEquals("Building on a tile should increase its level by 1", 1, tile.getLevel());
        assertFalse("Building once should not automatically add a dome", tile.hasDome());
    }

    @Test
    public void testBuildAddsDomeAtFourthLevel() {
        for (int i = 0; i < 3; i++) {
            tile.build();
        }
        assertTrue("Building four times should add a dome", tile.build());
        assertTrue("Tile should have a dome after fourth build", tile.hasDome());
    }

    @Test
    public void testNoBuildWhenDomeExists() {
        for (int i = 0; i < 4; i++) {
            tile.build();
        }
        assertFalse("Should not be able to build once dome is placed", tile.build());
    }

    @Test
    public void testMoveWorkerTo() {
        Tile destinationTile = new Tile(0, 1);
        tile.setWorker(worker);
        tile.moveWorkerTo(destinationTile);
        assertTrue("Destination tile should have the worker after moving", destinationTile.isOccupied());
        assertNull("Original tile should not have the worker after moving", tile.getWorker());
    }
}
