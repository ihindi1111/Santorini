package godcards;

public class Pan implements IWinStrategy {
    public boolean checkForWin(Worker worker, Tile fromTile, Tile toTile) {
        if (toTile.getLevel() - fromTile.getLevel() >= 2) {
            return true;
        }
        return false;
    }
}
