package components;

public interface IBuildStrategy {
    boolean performBuild(Worker worker, Tile previousTile, Tile buildTile);
}
