import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import components.Board;
import components.Player;
import components.Tile;
import components.Worker;
import godcards.Apollo;

public class ApolloTest {
    private Board board;
    private Player player;
    private Apollo apollo;
    private Worker worker;

    @Before
    public void setUp() {
        board = new Board();
        player = new Player(1);
        apollo = new Apollo();
        worker = new Worker(1, 1, 1);
        // Instead of a private method, simulate placing the worker directly on the tile
        board.getTile(1, 1).setWorker(worker);
        player.setGodStrategy(apollo);
    }

    @Test
    public void testValidMoveAdjacentEmptyTile() {
        assertFalse("Worker should not move diagonally", apollo.isValidAction(player, worker, board, 2, 2));
        assertTrue("Worker should move to adjacent empty tile", apollo.isValidAction(player, worker, board, 1, 2));
    }

    @Test
    public void testValidMoveWithSwap() {
        Worker opponentWorker = new Worker(2, 1, 2);
        board.getTile(1, 2).setWorker(opponentWorker); // Directly set the opponent worker on the tile
        assertTrue("Worker should swap places with opponent", apollo.isValidAction(player, worker, board, 1, 2));
    }

    @Test
    public void testPerformMove() {
        apollo.performAction(player, worker, board, 1, 2);
        assertNull("Original tile should be empty", board.getTile(1, 1).getWorker());
        assertSame("Worker should be on new tile", worker, board.getTile(1, 2).getWorker());
    }

    @Test
    public void testPerformSwap() {
        Worker opponentWorker = new Worker(2, 1, 2);
        board.getTile(1, 2).setWorker(opponentWorker); // Directly set the opponent worker on the tile
        apollo.performAction(player, worker, board, 1, 2);
        assertSame("Worker should be on new tile", worker, board.getTile(1, 2).getWorker());
        assertSame("Opponent should be on original tile", opponentWorker, board.getTile(1, 1).getWorker());
    }

    @Test
    public void testInvalidMoveOntoDome() {
        board.getTile(1, 2).placeDome();
        assertFalse("Worker should not move onto a dome", apollo.isValidAction(player, worker, board, 1, 2));
    }
}
