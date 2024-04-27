package godcards;

import components.Board;
import components.Tile;
import components.Worker;
import components.Player;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DemeterTest {
    private Board board;
    private Player player;
    private Demeter demeter;
    private Worker worker;

    @Before
    public void setUp() {
        board = new Board();
        player = new Player(1);
        demeter = new Demeter();
        worker = new Worker(1, 1, 1);
        board.getTile(1, 1).setWorker(worker); // Set worker at a known position
        player.setGodStrategy(demeter);
    }

    @Test
    public void testValidFirstBuild() {
        assertTrue("Should allow first valid build", demeter.isValidAction(player, worker, board, 1, 2));
    }

    @Test
    public void testSecondBuildOnSameTile() {
        demeter.performAction(player, worker, board, 1, 2); // Perform first build
        assertFalse("Should not allow second build on the same tile", demeter.isValidAction(player, worker, board, 1, 2));
    }

    @Test
    public void testSecondBuildOnDifferentTile() {
        demeter.performAction(player, worker, board, 1, 2); // Perform first build
        assertTrue("Should allow second build on a different adjacent tile", demeter.isValidAction(player, worker, board, 1, 0));
    }

    @Test
    public void testPassSecondBuild() {
        demeter.performAction(player, worker, board, 1, 2); // Perform first build
        assertTrue("Should allow passing the second build by attempting to build on the worker's tile", demeter.isValidAction(player, worker, board, 1, 1));
    }

    @Test
    public void testPerformSecondBuild() {
        demeter.performAction(player, worker, board, 1, 2); // First build
        demeter.performAction(player, worker, board, 1, 0); // Second build on a different tile
        assertEquals("Tile should have level 1", 1, board.getTile(1, 2).getLevel());
        assertEquals("Tile should have level 1", 1, board.getTile(1, 0).getLevel());
    }

    @Test
    public void testResetAfterTurn() {
        demeter.performAction(player, worker, board, 1, 2); // First build
        demeter.performAction(player, worker, board, 1, 0); // Second build
        assertFalse("Demeter's firstBuild should reset to false at the end of the turn", demeter.hasPerformedFirstAction());
    }
}