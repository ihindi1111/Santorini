package godcards;

public class DemeterBuildStrategy implements IBuildStrategy {

    @Override
    public boolean performBuild(Worker worker, Tile previousTile, Tile buildTile) {
        if (buildTile.equals(previousTile)) {
            return false;  // Cannot build on the same tile twice
        }
        if (buildTile.build()) {
            return true;
        }
        return false;
    }
}