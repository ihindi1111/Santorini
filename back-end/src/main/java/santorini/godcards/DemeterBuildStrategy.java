package godcards;

import components.Tile;
import components.Worker;
import components.IBuildStrategy;

public class DemeterBuildStrategy implements IBuildStrategy {

    @Override
    public boolean performBuild(Worker worker, Tile previousTile, Tile buildTile) {
        if (buildTile.getX() == previousTile.getX() && buildTile.getY() == previousTile.getY()) {
            return false;  // Cannot build on the same tile twice
        }
        if (buildTile.build()) {
            return true;
        }
        return false;
    }
}