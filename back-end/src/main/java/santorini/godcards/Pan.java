package godcards;

import components.Tile;
import components.Worker;
import interfaces.IWinStrategy;
import components.Board;
import components.Player;

import interfaces.GodStrategy;

public class Pan implements GodStrategy {
    
    @Override
    public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
        if (board.getTile(worker.getX(), worker.getY()).getLevel() - board.getTile(x,y).getLevel() >= 2) {
            return true;
        }
        return false;
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        if (board.getTile(worker.getX(), worker.getY()).getLevel() - board.getTile(x,y).getLevel() >= 2) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPerformedFirstAction() {
        return false;
    }

    @Override
    public boolean hasSecondAction() {
        return false;
    }

    @Override
    public boolean hasMove() {
        return false;
    }

    @Override
    public boolean hasBuild() {
        return false;
    }

    @Override
    public boolean hasWin() {
        return true;
    }
}
