package edu.cmu.cs214.hw3.santorini.backend.games;

import edu.cmu.cs.cs214.hw3.santorini.backend.Game;
import edu.cmu.cs.cs214.hw3.santorini.backend.Player;
import edu.cmu.cs.cs214.hw3.santorini.backend.Board;
import edu.cmu.cs.cs214.hw3.santorini.backend.Tile;
import edu.cmu.cs.cs214.hw3.santorini.backend.TurnPhase;

import java.util.ArrayList;
import java.util.List;

public final class Santorini {
    private Board board;
    private List<Player> players;
    private Player currPlayer;
    private boolean gameWon;
    public static final int SIZE = 5;
    private TurnPhase currentPhase = TurnPhase.PLACE_WORKERS;
    private boolean workersPlaced = false;
    private Worker selectedWorker;

    /**
     * Constructor for Santorini game initializes the board and players.
     */
    public Santorini() {
        this.board = new Board();
        this.players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            players.add(new Player(i));
        }
        currPlayer = players.get(0);
        this.gameWon = false;
    }

    
    /**
     * Switches the active player to the next one in the list.
     */
    public void switchPlayer() {
        int nextPlayerIndex = (players.indexOf(currPlayer) + 1) % players.size();
        this.currPlayer = players.get(nextPlayerIndex);
    }

    /**
     * Checks if a worker has reached the third level of a tower
     * @param worker The worker to check for a win condition
     * @return True if the worker has reached the third level, false otherwise
     */
    public boolean checkForWin(Worker worker) {
        return board.getTile(worker.getX(), worker.getY()).getLevel() == 3;
    }

    /**
     * Checks if the game has been won
     * @return True if the game has been won, false otherwise
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Returns the current player
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return currPlayer;
    }

    // Builds a structure on the board
    public boolean build(Player player, int workerNum, int buildX, int buildY) {
        if (!game.getCurrentPlayer().equals(player)) {
            System.out.println("It's not this player's turn");
            return false;
        }
        return game.build(workerNum, buildX, buildY);
    }

    public boolean isValidBuild(Player player, int workerNum, int buildX, int buildY) {
        if (!game.getCurrentPlayer().equals(player)) {
            System.out.println("Not this Player's turn");
            return false;
        }
        // This logic assumes there's a method in Game or Board for just validating a build
        return game.getBoard().isValidBuild(currentPlayer.getWorker(workerNum), buildX, buildY);
    }

    /**
     * Attempts to move a worker on the board.
     * @param player The player making the move.
     * @param workerNum The worker number attempting the move.
     * @param moveX The x-coordinate for the move.
     * @param moveY The y-coordinate for the move.
     * @return true if the move was successful, false otherwise.
     */
    public boolean tryMove(Player player, int workerNum, int moveX, int moveY) {
        if (!game.getCurrentPlayer().equals(player)) {
            System.out.println("It's not this player's turn.");
            return false;
        }
        if (!isValidMove(player, workerNum, moveX, moveY)) {
            System.out.println("Invalid move. Please try again.");
            return false;
        } 
        if (!game.moveWorker(workerNum, moveX, moveY)) {
            System.out.println("Failed to move. Please try again.");
            return false;
        }
        return true;
    }

    /**
     * Attempts to build on the board after a successful move.
     * @param player The player attempting the build.
     * @param workerNum The worker number performing the build.
     * @param buildX The x-coordinate for the build.
     * @param buildY The y-coordinate for the build.
     * @return true if the build was successful, false otherwise.
     */
    public boolean tryBuild(Player player, int workerNum, int buildX, int buildY) {
        if (!isValidBuild(player, workerNum, buildX, buildY)) {
            System.out.println("Invalid build. Please try again.");
            return false;
        } 
        if (!game.build(workerNum, buildX, buildY)) {
            System.out.println("Failed to build. Please try again.");
            return false;
        }
        return true;
    }

    /**
     * Executes a complete play turn, including move and build actions.
     * This method assumes external management of the play loop and only orchestrates a single attempt of move and build.
     * @param player The player executing the play turn.
     * @param workerNum The worker being used for the turn.
     * @param moveX The x-coordinate for the move.
     * @param moveY The y-coordinate for the move.
     * @param buildX The x-coordinate for the build.
     * @param buildY The y-coordinate for the build.
     */
    public void playTurn(Player player, int workerNum, int moveX, int moveY, int buildX, int buildY) {
        boolean moveSuccessful = tryMove(player, workerNum, moveX, moveY);
        
        if (moveSuccessful && game.checkForWin(workerNum)) {
            System.out.println("Game won by player.");
            return; // Game ends after a win
        }

        boolean buildSuccessful = moveSuccessful && tryBuild(player, workerNum, buildX, buildY);

        if (moveSuccessful && buildSuccessful) {
            game.switchPlayer(); // Switch turn to the next player if both move and build were successful
        }
    }

    // Checks if the game has been won
    public boolean checkGameWon() {
        return game.isGameWon();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    // Retrieves the current game state, such as the board and player information
    // This could be converted to JSON or another format for communication with a frontend
    public Game getGameState() {
        return game;
    }

    // Main method for testing or standalone console-based gameplay
    public static void main(String[] args) {
        Santorini santorini = new Santorini();
        santorini.startNewGame();
    }
}
