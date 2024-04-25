package godcards;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;

public class Hephaestus implements IBuildStrategy {
    @Override
    public boolean performBuild(Worker worker, Tile previousTile, int x, int y) {
        if (isValidBuild(worker, previousTile, x, y)) {  // Only add a block if it won't create a dome
            buildTile.build();  // Add another block
            return true;
        }
        return false;
    }

    public boolean isValidBuild(Worker worker, Tile previousTile, int x, int y) {
        if (x != previousTile.getX() && y != previousTile.getY()) return false;  // Must build on the same tile twice
        return true;
    }
}
