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
    
    @Override
    public boolean isMoveValid(int x, int y) {
        // Santorini does not use this method directly for move validation; it uses more complex logic.
        return false;
    }

    @Override
    public boolean isMoveOver() {
        // In Santorini, a move is over after a player has moved and built. This is managed within the playTurn method.
        return false;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        // This method could be used to handle a move being played, but Santorini requires more information.
    }

    @Override
    public boolean isGameOver() {
        return game.checkGameWon();
    }

    @Override
    public String getGameOverMessage() {
        if (isGameOver()) {
            return "The game has been won!";
        }
        return "Game is still ongoing.";
    }

    @Override
    public void onGameClosed() {
        // Perform any cleanup if necessary.
    }

    @Override
    public Player currentPlayer() {
        return game.getCurrentPlayer();
    }
}