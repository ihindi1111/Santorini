package godcards;

import components.Tile;
import components.Worker;
import interfaces.IWinStrategy;
import components.Board;

public class Pan implements IWinStrategy {
    public boolean checkForWin(Tile startTile, Tile endTile) {
        if (startTile.getLevel() - endTile.getLevel() >= 2) {
            return true;
        }
        return false;
    }
}
