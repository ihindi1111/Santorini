import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import components.Board;
import components.Player;
import components.Tile;
import components.Worker;

import godcards.Minotaur;

public class MinotaurTest {
    private Board board;
    private Player player;
    private Minotaur minotaur;
    private Worker worker;
    private Worker opponentWorker;

    @Before
    public void setUp() {
        board = new Board();
        player = new Player(1);
        minotaur = new Minotaur();
        worker = new Worker(1, 1, 1);
        board.getTile(1, 1).setWorker(worker);
        player.setGodStrategy(minotaur);
    }

    @Test
    public void testPushOpponent() {
        opponentWorker = new Worker(2, 1, 2);
        board.getTile(1, 2).setWorker(opponentWorker); // Set opponent in front of the Minotaur
        assertTrue("Minotaur should push the opponent worker", minotaur.isValidAction(player, worker, board, 1, 2));
        minotaur.performAction(player, worker, board, 1, 2);
        assertEquals("Opponent should be pushed to (1,3)", opponentWorker, board.getTile(1, 3).getWorker());
        assertEquals("Minotaur should move to (1,2)", worker, board.getTile(1, 2).getWorker());
    }

    @Test
    public void testInvalidPush() {
        opponentWorker = new Worker(2, 1, 2);
        board.getTile(1, 2).setWorker(opponentWorker); // Set opponent in front of the Minotaur
        board.getTile(1, 3).setWorker(new Worker(1, 1, 3)); // Block the push destination with another worker
        assertFalse("Should not push if destination is occupied", minotaur.isValidAction(player, worker, board, 1, 2));
    }

    @Test
    public void testMoveWithoutOpponent() {
        assertTrue("Minotaur should move to adjacent empty tile", minotaur.isValidAction(player, worker, board, 1, 0));
        minotaur.performAction(player, worker, board, 1, 0);
        assertEquals("Minotaur should move to (1,0)", worker, board.getTile(1, 0).getWorker());
        assertNull("Original position should be empty", board.getTile(1, 1).getWorker());
    }

    @Test
    public void testNoMoveOntoDome() {
        board.getTile(1, 0).placeDome();
        assertFalse("Should not move onto a dome", minotaur.isValidAction(player, worker, board, 1, 0));
    }

    @Test
    public void testMoveDiagonally() {
        assertFalse("Minotaur should not move diagonally", minotaur.isValidAction(player, worker, board, 2, 2));
    }
}