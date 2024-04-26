package interfaces;

import components.Board;
import components.Worker;
import components.Tile;

public interface IWinStrategy {
    boolean checkForWin(Tile startTile, Tile endTile);
}
