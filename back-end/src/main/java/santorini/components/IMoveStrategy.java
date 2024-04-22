package components;

public interface IMoveStrategy {
    boolean isValidMove(Worker worker, Tile fromTile, Tile toTile);
}
