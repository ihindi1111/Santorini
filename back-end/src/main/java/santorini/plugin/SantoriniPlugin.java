package plugin;

import framework.core.GameFrameworkImpl;
import framework.core.GameFramework;
import framework.core.GamePlugin;
import games.Santorini;
import components.Player;
import components.Tile;
import components.TurnPhase;
import components.Worker;
import components.TurnPhase;
import components.GodCardManager;

public class SantoriniPlugin implements GamePlugin<String> {
    private static final String GAME_NAME = "Santorini";

    private static final int WIDTH = Santorini.SIZE;
    private static final int HEIGHT = Santorini.SIZE;

    private static final String PLAYER_WON_MSG = "%s won!";

    private String GAME_START_FOOTER = "You are playing Santorini!";
    private GameFramework framework;
    private Santorini game;
    private String[] godCardGrid = new String[WIDTH * HEIGHT];

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
        setupGodCardSelection();
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
        if (game.getPhase().equals(TurnPhase.SELECT_GOD_CARD)) {
            game.handleGodChoice(x, y);
        }
        else {
            game.play(x, y);
            updateBoardVisuals();
        }
        updateFooterText();
        // game.play(x, y);
        // framework.setSquare(x, y, game.getBoard().getTile(x, y).visualRepresentation());
        // updateBoardVisuals();
        // updateFooterText();
    }

    private void setupGodCardSelection() {
        // Use godCardManager to set up UI elements
        for (int i = 0; i < Santorini.SIZE * Santorini.SIZE; i++) {
            int x = i % Santorini.SIZE;
            int y = i / Santorini.SIZE;
            String cardName = game.getGodCardManager().getCardAtPosition(x, y, Santorini.SIZE);
            if (cardName != null) {
                framework.setSquare(x, y, cardName);
            }
        }
    }

    public String getGodCardNameAtPosition(int x, int y) {
        int index = y * WIDTH + x;
        return index >= 0 && index < godCardGrid.length ? godCardGrid[index] : null;
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
            case SELECT_GOD_CARD:
                GAME_START_FOOTER = String.format("%s, select a God card", game.getCurrentPlayer().toString());
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

    public String getGameOverMessage() {
        if (isGameOver()) {
            return String.format(PLAYER_WON_MSG, game.getCurrentPlayer());
        }
        return "Game is still ongoing.";
    }

    public void onGameClosed() {
        // Perform any cleanup if necessary.
    }

    public String currentPlayer() {
        Player currentPlayer = game.getCurrentPlayer(); // Assuming `game` is your Santorini game instance
        if (currentPlayer != null) {
            return currentPlayer.toString();
        } else {
            // Handle the null case, perhaps by returning a placeholder string
            return "No current player";
        }
    }    

    @Override
    public boolean isMoveValid(int x, int y) {
        // Check if the move is within the board boundaries
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return false;
        }

        // Check the validity based on the current phase of the game
        switch (game.getPhase()) {
            case SELECT_GOD_WORKER:
                // Check if the chosen square corresponds to a selectable god card
                String godCardName = game.getGodCardManager().getCardAtPosition(x, y, WIDTH);
                return godCardName != null && game.getGodCards().isValidSelection(godCardName);
            case PLACE_WORKERS:
                // During placement, any unoccupied square is a valid move
                return !game.getBoard().getTile(x, y).isOccupied();
            case SELECT_WORKER:
                // Here you would check if the selected square has the player's worker
                Worker worker = game.getBoard().getTile(x, y).getWorker();
                if (worker != null && worker.getPlayer() == game.getCurrentPlayer().getPlayerID()) {
                    game.selectWorker(x, y);
                    return true;
                }
                else return false;
            case MOVE:
                // Validate the move only if a worker has been selected
                Worker selectedWorker = game.getSelectedWorker();
                return selectedWorker != null && game.getBoard().isValidMove(game.getCurrentPlayer(), selectedWorker, x, y);
            case BUILD:
                // Validate the build only if a worker has been selected
                selectedWorker = game.getSelectedWorker();
                return selectedWorker != null && game.getBoard().isValidBuild(game.getCurrentPlayer(), selectedWorker, x, y);
            default:
                // If it's not one of the above phases, no moves should be valid
                return false;
        }
    }
}