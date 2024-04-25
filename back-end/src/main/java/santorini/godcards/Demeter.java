package godcards;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;

public class Demeter implements IBuildStrategy {

    @Override
    public boolean performBuild(Worker worker, Tile previousTile, Tile buildTile) {
        if (isValidBuild(worker, buildTile, buildTile)) {
            buildTile.build();
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidBuild(Worker worker, Tile previousTile, Tile buildTile) {
        if (buildTile.getX() == previousTile.getX() && buildTile.getY() == previousTile.getY()) return false;
        return true;
    }
}