package main;

public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayer;

    public Game() {
        this.board = new Board();
        this.players = new Player[]{new Player(), new Player()};
        this.currentPlayer = 0; //Starts with Player 1
    }

    public void newGame() {
        board.reset();
        currentPlayer = 0;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % players.length;
    }

    public boolean checkWin() {
        return false;
    }
}
