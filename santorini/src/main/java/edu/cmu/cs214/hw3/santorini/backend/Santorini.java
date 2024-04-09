package edu.cmu.cs214.hw3.santorini.backend;

public final class Santorini {
    private Game game;
    public static final int SIZE = 5;

    public Santorini() {
        this.game = new Game();
    }

    // Starts a new game
    public void startNewGame() {
        game.newGame();
    }

    // Places a worker on the board for the current player
    public boolean placeWorker(int playerIndex, int workerNum, int x, int y) {
        if (game.getCurrentPlayerIndex() != playerIndex) {
            System.out.println("It's not this player's turn");
            return false;
        }
        return game.placeWorker(workerNum, x, y);
    }

    public boolean isValidMove(int playerIndex, int workerNum, int newX, int newY) {
        if (game.getCurrentPlayerIndex() != playerIndex) {
            System.out.println("It is not this player's turn");
            return false;
        }
        return game.getBoard().isValidMove(game.getPlayers().get(playerIndex).getWorker(workerNum), newX, newY);
    }

    // Moves a worker on the board
    public boolean moveWorker(int playerIndex, int workerNum, int newX, int newY) {
        if (game.getCurrentPlayerIndex() != playerIndex) {
            System.out.println("It's not this player's turn");
            return false;
        }
        return game.moveWorker(workerNum, newX, newY);
    }

    // Builds a structure on the board
    public boolean build(int playerIndex, int workerNum, int buildX, int buildY) {
        if (game.getCurrentPlayerIndex() != playerIndex) {
            System.out.println("It's not this player's turn");
            return false;
        }
        return game.build(workerNum, buildX, buildY);
    }

    public boolean isValidBuild(int playerIndex, int workerNum, int buildX, int buildY) {
        if (game.getCurrentPlayerIndex() != playerIndex) {
            System.out.println("Not this Player's turn");
            return false;
        }
        // This logic assumes there's a method in Game or Board for just validating a build
        return game.getBoard().isValidBuild(game.getPlayers().get(playerIndex).getWorker(workerNum), buildX, buildY);
    }

    // Checks if the game has been won
    public boolean checkGameWon() {
        return game.isGameWon();
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
        // Add logic here for a simple CLI-based game or testing
    }
}
