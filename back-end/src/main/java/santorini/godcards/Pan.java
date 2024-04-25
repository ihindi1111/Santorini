package godcards;

import components.Tile;
import components.Worker;
import interfaces.IWinStrategy;
import components.Board;

public class Pan implements IWinStrategy {
    public boolean checkForWin(Worker worker, Tile fromTile, Tile toTile) {
        if (toTile.getLevel() - fromTile.getLevel() >= 2) {
            return true;
        }
        return false;
    }
}
