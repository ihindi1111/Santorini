package edu.cmu.cs214.hw3.santorini.backend;

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
