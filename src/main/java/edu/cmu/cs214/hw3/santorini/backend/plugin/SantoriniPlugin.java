package edu.cmu.cs214.hw3.santorini.backend.plugin;

import edu.cmu.cs214.hw3.santorini.backend.core.GameFrameworkImpl;
import edu.cmu.cs214.santorini.backend.hw3.core.GameFramework;
import edu.cmu.cs214.santorini.backend.hw3.core.GamePlugin;
import edu.cmu.cs214.santorini.backend.games.Santorini;
import edu.cmu.cs214.santorini.backend.hw3.Player;
import edu.cmu.cs214.santorini.backend.hw3.Tile;
import edu.cmu.cs214.santorini.backend.hw3.TurnPhase;
import edu.cmu.cs214.santorini.backend.hw3.Worker;

public class SantoriniPlugin implements GamePlugin<Player> {
    private static final String GAME_NAME = "Santorini";

    private static final int WIDTH = Santorini.SIZE;
    private static final int HEIGHT = Santorini.SIZE;

    private static final String PLAYER_WON_MSG = "%s won!";

    private String GAME_START_FOOTER = "You are playing Santorini!";
    private GameFramework framework;
    private Santorini game;

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
        this.game = new Santorini();
    }

    @Override
    public void onNewGame() {
        game = new Santorini();
        framework.setFooterText(GAME_START_FOOTER);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                framework.setSquare(x, y, ""); // Clear the board
            }
        }
    }

    @Override
    public void onNewMove() {
        // This method could be used to prepare for a new move, but Santorini's state is managed internally.
    }

    public boolean isMoveOver() {
        return game.getPhase().equals(TurnPhase.END_TURN);
    }

    public void updateBoardVisuals() {
        for (int y = 0; y < Santorini.SIZE; y++) {
            for (int x = 0; x < Santorini.SIZE; x++) {
                Tile tile = game.getBoard().getTile(x, y);
                framework.setSquare(x, y, tile.visualRepresentation());
            }
        }
    }

    public void onMovePlayed(int x, int y) {
        game.play(x, y);
        framework.setSquare(x, y, game.getBoard().getTile(x, y).visualRepresentation());
        updateBoardVisuals();
        updateFooterText();
    }

    private void updateFooterText() {
        if (game.isGameWon()) {
            GAME_START_FOOTER = String.format(PLAYER_WON_MSG, game.getCurrentPlayer().toString());
        }
        switch (game.getPhase()) {
            case PLACE_WORKERS:
                GAME_START_FOOTER = "Place your workers on the board.";
                break;
            case SELECT_WORKER:
                GAME_START_FOOTER = String.format("%s, select a worker to move.", game.getCurrentPlayer().toString());
                break;
            case MOVE:
                GAME_START_FOOTER = "Move your selected worker to an adjacent square.";
                break;
            case BUILD:
                GAME_START_FOOTER = "Build a block or dome adjacent to your worker.";
                break;
            case END_TURN:
                GAME_START_FOOTER = String.format("%s has ended their turn.", game.getCurrentPlayer().toString());
                break;
            default:
                GAME_START_FOOTER = "Unexpected phase. Check the game state.";
                break;
        }
        framework.setFooterText(GAME_START_FOOTER);
    }

    public boolean isGameOver() {
        return game.isGameWon();
    }
}