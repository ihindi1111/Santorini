import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import components.Board;
import components.Player;
import components.Tile;
import components.Worker;
import godcards.Artemis;

public class ArtemisTest {
    private Board board;
    private Player player;
    private Artemis artemis;
    private Worker worker;

    @Before
    public void setUp() {
        board = new Board();
        player = new Player(1);
        artemis = new Artemis();
        worker = new Worker(1, 1, 1);
        board.getTile(1, 1).setWorker(worker);
        player.setGodStrategy(artemis);
    }

    @Test
    public void testValidFirstMove() {
        assertTrue("Should allow move to adjacent empty tile", artemis.isValidAction(player, worker, board, 1, 2));
        assertFalse("Should not allow move to non-adjacent tile", artemis.isValidAction(player, worker, board, 3, 3));
    }

    @Test
    public void testPerformFirstMove() {
        artemis.performAction(player, worker, board, 1, 2);
        assertNull("Worker should have moved from the original tile", board.getTile(1, 1).getWorker());
        assertNotNull("Worker should be on the new tile", board.getTile(1, 2).getWorker());
        assertTrue("First move done should be true", artemis.hasPerformedFirstAction());
    }

    @Test
    public void testValidSecondMove() {
        artemis.performAction(player, worker, board, 1, 2);
        assertTrue("Should allow 'pass' by staying on the same tile", artemis.isValidAction(player, worker, board, 1, 2));
    }

    @Test
    public void testPerformSecondMove() {
        artemis.performAction(player, worker, board, 1, 2);
        artemis.performAction(player, worker, board, 2, 2);
        assertNull("Worker should have moved from the second tile", board.getTile(1, 2).getWorker());
        assertNotNull("Worker should be on the new tile", board.getTile(2, 2).getWorker());
        assertFalse("First move done should reset to false", artemis.hasPerformedFirstAction());
    }

    @Test
    public void testPassSecondMove() {
        artemis.performAction(player, worker, board, 1, 2);
        artemis.performAction(player, worker, board, 1, 2); // Pass action
        assertNotNull("Worker should remain on the same tile", board.getTile(1, 2).getWorker());
        assertTrue("Should reset after passing the second move", !artemis.hasPerformedFirstAction());
    }
}