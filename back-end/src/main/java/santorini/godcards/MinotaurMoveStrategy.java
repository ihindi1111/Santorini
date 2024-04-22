package godcards;

import components.Tile;
import components.Worker;
import components.IBuildStrategy;

public class MinotaurMoveStrategy {
    @Override
    public boolean isValidMove(Worker worker, Tile fromTile, Tile toTile) {
        if (toTile.isOccupied() && !toTile.hasDome()) {
            Tile behindTile = board.getTile(toTile.getX() * 2 - fromTile.getX(), toTile.getY() * 2 - fromTile.getY());
            if (behindTile != null && !behindTile.isOccupied() && !behindTile.hasDome()) {
                // Move the opponent's worker to behindTile
                toTile.getWorker().setPosition(behindTile.getX(), behindTile.getY());
                behindTile.setWorker(toTile.getWorker());
                toTile.setWorker(null);
                return true;
            }
            return false;
        }
        return !toTile.isOccupied() && !toTile.hasDome();
    }
}
