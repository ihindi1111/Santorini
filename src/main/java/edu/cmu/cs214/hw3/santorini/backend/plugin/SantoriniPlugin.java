package edu.cmu.cs214.hw3.santorini.backend.plugin;

import edu.cmu.cs214.hw3.santorini.backend.core.GameFrameworkImpl;
import edu.cmu.cs214.santorini.backend.hw3.core.GameFramework;
import edu.cmu.cs214.santorini.backend.hw3.core.GamePlugin;
import edu.cmu.cs214.santorini.backend.hw3.Board;

public class SantoriniPlugin implements GamePlugin<Player> {
    private static final String GAME_NAME = "Santorini";
    private GameFramework framework;
    private Santorini game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return Santorini.SIZE;
    }

    @Override
    public int getGridHeight() {
        return Santorini.SIZE;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
        this.game = new Santorini();
    }

    @Override
    public void onNewGame() {
        game.startNewGame();
    }

    @Override
    public void onNewMove() {
        // This method could be used to prepare for a new move, but Santorini's state is managed internally.
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