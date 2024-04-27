import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import interfaces.GodStrategy;

import components.Player;
import components.Board;
import components.Worker;



public class PlayerTest {
    private Player player;
    private GodStrategy mockStrategy;

    @Before
    public void setUp() {
        player = new Player(1);
        mockStrategy = new GodStrategy() {
            @Override
            public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
                return true; // Simplify for testing
            }

            @Override
            public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
                return true; // Simplify for testing
            }

            @Override
            public boolean hasPerformedFirstAction() {
                return false;
            }

            @Override
            public boolean hasSecondAction() {
                return false;
            }

            @Override
            public int hasNum() {
                return 1;
            }
        };
    }

    @Test
    public void testGetWorker() {
        assertNotNull("Worker should not be null", player.getWorker(0));
        assertNotNull("Worker should not be null", player.getWorker(1));
        assertEquals("Workers should belong to the same player", 1, player.getWorker(0).getPlayer());
    }

    @Test
    public void testPlayerID() {
        assertEquals("Player ID should match constructor argument", 1, player.getPlayerID());
    }

    @Test
    public void testPlayerName() {
        assertEquals("Player name should be set correctly", "Player 1", player.toString());
    }

    @Test
    public void testGodStrategy() {
        assertNull("Initially no GodStrategy should be set", player.getGodStrategy());
        player.setGodStrategy(mockStrategy);
        assertNotNull("GodStrategy should be set", player.getGodStrategy());
        assertTrue("Should confirm GodStrategy is set", player.hasGodStrategy());
    }
}