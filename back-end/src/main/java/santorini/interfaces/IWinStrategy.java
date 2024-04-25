package interfaces;

import components.Board;
import components.Worker;

public interface IWinStrategy {
    boolean checkForWin(Worker worker, Board board, int x, int y);
}
