package godcards;

import components.Tile;
import components.Worker;
import interfaces.IWinStrategy;
import components.Board;

public class Pan implements IWinStrategy {
    public boolean checkForWin(Worker worker, Board board, int x, int y) {
        if (board.getTile(worker.getX(), worker.getY()).getLevel() - board.getTile(x, y).getLevel() >= 2) {
            return true;
        }
        return false;
    }
}
