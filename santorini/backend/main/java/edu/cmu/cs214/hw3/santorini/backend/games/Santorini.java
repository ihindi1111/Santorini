package edu.cmu.cs214.hw3.santorini.backend.games;

public final class Santorini {
    private Game game;
    public static final int SIZE = 5;

    private Player currentPlayer;

    public Santorini() {
        this.game = new Game();
    }

    // Starts a new game
    public void startNewGame() {
        game.newGame();
        currentPlayer = game.getCurrentPlayer();
    }

    // Places a worker on the board for the current player
    public boolean placeWorker(Player player, int workerNum, int x, int y) {
        if (!game.getCurrentPlayer().equals(player)) {
            System.out.println("It's not this player's turn");
            return false;
        }
        return game.placeWorker(workerNum, x, y);
    }

    public boolean isValidMove(Player player, int workerNum, int newX, int newY) {
        if (!game.getCurrentPlayer().equals(player)) {
            System.out.println("It is not this player's turn");
            return false;
        }
        return game.getBoard().isValidMove(currentPlayer.getWorker(workerNum), newX, newY);
    }

    // Moves a worker on the board
    public boolean moveWorker(Player player, int workerNum, int newX, int newY) {
        if (!game.getCurrentPlayer().equals(player)) {
            System.out.println("It's not this player's turn");
            return false;
        }
        return game.moveWorker(workerNum, newX, newY);
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
     * Performs a play action where a player attempts to move and then build.
     * If the move or build is invalid, the player is prompted to retry.
     * @param player The player making the move.
     * @param workerNum The worker number attempting the action.
     * @param moveX The x-coordinate for the move.
     * @param moveY The y-coordinate for the move.
     * @param buildX The x-coordinate for the build.
     * @param buildY The y-coordinate for the build.
     */
    public void play(Player player, int workerNum, int moveX, int moveY, int buildX, int buildY) {
        boolean validPlay = false;

        while (!validPlay) {
            // Validate the player's turn
            if (!game.getCurrentPlayer().equals(player)) {
                System.out.println("It's not this player's turn.");
                break; // Or return, depending on how strict you want to enforce turn order.
            }

            // Attempt to move
            if (!isValidMove(player, workerNum, moveX, moveY)) {
                System.out.println("Invalid move. Please try again.");
                // Here, in a real application, you'd likely gather new inputs for moveX and moveY before retrying.
                // For this example, assume moveX and moveY are updated somehow.
                continue; // Go to the next iteration of the loop to retry.
            } else if (!game.moveWorker(workerNum, moveX, moveY)) {
                // Move failed for some unexpected reason.
                System.out.println("Failed to move. Please try again.");
                continue;
            }

            // Attempt to build
            if (!isValidBuild(player, workerNum, buildX, buildY)) {
                System.out.println("Invalid build. Please try again.");
                // Here, in a real application, you'd likely gather new inputs for buildX and buildY before retrying.
                // Assume buildX and buildY are updated somehow for retry.
                // Optionally, you might want to revert the move action here if the move-build is atomic.
                continue; // Go to the next iteration of the loop to retry.
            } else if (!game.build(workerNum, buildX, buildY)) {
                // Build failed for some unexpected reason.
                System.out.println("Failed to build. Please try again.");
                continue;
            }

            validPlay = true; // If both move and build succeeded
        }

        if (validPlay) {
            // Handle post-play logic, such as checking for win conditions
            if (game.checkForWin(workerNum)) {
                System.out.println("Game won by player.");
            } else {
                game.switchPlayer(); // Switch turn to the next player
            }
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
