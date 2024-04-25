package interfaces;

import components.Board;
import components.Tile;
import components.Worker;

public interface IBuildStrategy {
    /**
     * Checks if a build operation is valid.
     * @param worker The worker performing the build.
     * @param previousTile The tile from which the worker is building.
     * @param buildTile The tile on which the building is attempted.
     * @return true if the build is valid, false otherwise.
     */
    boolean isValidBuild(Worker worker, Board board, Tile previousTile, int x, int y);

    /**
     * Performs the build operation on the specified tile.
     * @param worker The worker performing the build.
     * @param previousTile The tile from which the worker is building.
     * @param buildTile The tile on which the build is being executed.
     * @return true if the build was successfully performed, false otherwise.
     */
    boolean performBuild(Worker worker, Tile previousTile, int x, int y);

    boolean firstBuild();
    void setFirstBuild(boolean firstBuild);
}
