package main;

public class GameTest {
    private Board board;
    private Player[] players;
    private int currentPlayer;

    /**
     * Initializes a new Game with 2 players and starting at Player 1
     */
    public Game() {
        this.board = new Board();
        this.players = new Player[]{new Player(), new Player()};
        this.currentPlayer = 0; //Starts with Player 1
    }

    /**
     * Resets the game
     */
    public void newGame() {
        board.reset();
        currentPlayer = 0;
    }

    /**
     * Switches players
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % players.length;
    }

    /**
     * Checks if a player has won on their turn
     * @return true if on level 3
     */
    public boolean checkWin(currentPlayer) {
        for (int worker = 0; worker < currentPlayer.workers.length(); worker++) {
            if (currentPlayer.getWorker(worker).getLevel == 3) return true;
            return false;
        }
    }
}
