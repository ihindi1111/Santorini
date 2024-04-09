package edu.cmu.cs214.hw3.santorini.backend.plugin;

import edu.cmu.cs214.hw3.santorini.backend.core.GameFrameworkImpl;
import edu.cmu.cs214.hw3.santorini.backend.core.GameFramework;
import edu.cmu.cs214.hw3.santorini.backend.core.GamePlugin;
import edu.cmu.cs214.hw3.santorini.backend.Board;

public class SantoriniPlugin implements GamePlugin<String> {
    private static final String GAME_NAME = "Santorini";

    private static final int WIDTH = Santorini.SIZE;
    private static final int HEIGHT = Santorini.SIZE;

    private static final String PLAYER_WON_MSG = "%s won!";

    private static final String GAME_START_FOOTER = "You are playing Santorini. Player 1 starts!";

    private GameFramework framework;
    private Santorini game;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        // Santorini board width.
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        // Santorini board height.
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    @Override
    public void onNewGame() {
        game.startNewGame();
        framework.setFooterText("Place your workers on the board.");
    }

    @Override
    public void onNewMove() {
        // Implementation depends on the current phase of the game (placing workers, moving, building).
    }

    @Override
    public boolean isMoveValid(Player player, int workerNum, int x, int y) {
        return game.isValidMove(game.getCurrentPlayer(), workerNum, x, y);
    }

    @Override
    public void onMovePlayed(int x, int y) {
        game.play()
    }

    @Override
    public boolean isGameOver() {
        // Check if the game has ended.
        return game.checkGameWon();
    }

    @Override
    public String getGameOverMessage() {
        // Return a message indicating the game's outcome.
        return "Player X has won!"; // Placeholder
    }

    @Override
    public void onGameClosed() {
        // Cleanup resources if needed.
    }

    @Override
    public String currentPlayer() {
        // Return the current player's name.
        return game.getCurrentPlayer().toString(); // Placeholder
    }

    @Override
    public boolean isMoveOver() {

    }
}
