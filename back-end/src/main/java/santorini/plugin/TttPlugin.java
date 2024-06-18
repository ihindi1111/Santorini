package plugin;

import framework.core.GameFrameworkImpl;
import framework.core.GameFramework;
import framework.core.GamePlugin;
import games.RockPaperScissors;
import games.TicTacToe;
import games.TicTacToe.Player;

public class TttPlugin implements GamePlugin<String> {
    private static final String GAME_NAME = "Tic Tac Toe";

    private static final int WIDTH = TicTacToe.SIZE;
    private static final int HEIGHT = TicTacToe.SIZE;

    private static final String PLAYER_WON_MSG = "%s won!";
    private static final String GAME_TIED_MSG = "The game ended in a tie.";

    private static final String GAME_START_FOOTER = "You are playing Tic Tac Toe. X starts the game!";

    private GameFramework framework;
    private TicTacToe game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        this.game = new TicTacToe(); // Initialize the TicTacToe game instance
        framework.setFooterText(GAME_START_FOOTER);
        // Initialize the game board in the GUI
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                framework.setSquare(x, y, ""); // Clear the board
            }
        }
    }


    @Override
    public void onNewMove() {
        // Not much to do for TicTacToe after each move.
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return game.isValidPlay(x, y);
    }

    @Override
    public boolean isMoveOver() {
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.getSquare(x, y).toString());
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public String getGameOverMessage() {
        if (game.winner() != null) {
            return String.format(PLAYER_WON_MSG, game.winner());
        } else {
            return GAME_TIED_MSG;
        }
    }

    @Override
    public void onGameClosed() {
        // Cleanup if needed.
    }

    @Override
    public String currentPlayer() {
        return game.currentPlayer().toString();
    }
}
