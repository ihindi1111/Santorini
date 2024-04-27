package godcards;

import components.Board;
import components.Tile;
import components.Worker;
import components.Player;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HephaestusTest {
    private Board board;
    private Player player;
    private Hephaestus hephaestus;
    private Worker worker;

    @Before
    public void setUp() {
        board = new Board();
        player = new Player(1);
        hephaestus = new Hephaestus();
        worker = new Worker(1, 2, 2);
        board.getTile(2, 2).setWorker(worker); // Set worker at a known position
        player.setGodStrategy(hephaestus);
    }

    @Test
    public void testValidFirstBuild() {
        assertTrue("Should allow first valid build", hephaestus.isValidAction(player, worker, board, 2, 3));
    }

    @Test
    public void testInvalidSecondBuildOnDifferentTile() {
        hephaestus.performAction(player, worker, board, 2, 3); // Perform first build
        assertFalse("Should not allow second build on a different tile", hephaestus.isValidAction(player, worker, board, 2, 1));
    }

    @Test
    public void testValidSecondBuildOnSameTile() {
        hephaestus.performAction(player, worker, board, 2, 3); // Perform first build
        assertTrue("Should allow second build on the same tile", hephaestus.isValidAction(player, worker, board, 2, 3));
    }

    @Test
    public void testPerformSecondBuild() {
        hephaestus.performAction(player, worker, board, 2, 3); // First build
        hephaestus.performAction(player, worker, board, 2, 3); // Second build on the same tile
        assertEquals("Tile should have level 2 and dome", 2, board.getTile(2, 3).getLevel());
    }

    @Test
    public void testNoBuildIfDomePresent() {
        board.getTile(2, 3).placeDome();
        assertFalse("Should not build if there is a dome", hephaestus.performAction(player, worker, board, 2, 3));
    }

    @Test
    public void testResetAfterBuild() {
        hephaestus.performAction(player, worker, board, 2, 3); // First build
        hephaestus.performAction(player, worker, board, 2, 3); // Second build
        assertFalse("Hephaestus's firstBuild should reset to false at the end of the build sequence", hephaestus.hasPerformedFirstAction());
    }
}