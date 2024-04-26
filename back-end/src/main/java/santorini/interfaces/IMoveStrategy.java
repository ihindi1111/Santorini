package interfaces;

import components.Board;
import components.Tile;
import components.Worker;

public interface IMoveStrategy {
    boolean isValidMove(Worker worker, Tile fromTile, Tile toTile, Board board);
    boolean performMove(Worker worker, int x, int y, Board board);
    boolean hasPerformedFirstMove();
    boolean hasSecondMove();
}
