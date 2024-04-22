package godcards;

import components.Tile;
import components.Worker;
import components.IBuildStrategy;

public class Hephaestus implements IBuildStrategy {
    @Override
    public boolean performBuild(Worker worker, Tile previousTile, Tile buildTile) {
        if (buildTile.getLevel() < 3) {  // Only add a block if it won't create a dome
            buildTile.build();  // Add another block
            return true;
        }
        return false;
    }
}
