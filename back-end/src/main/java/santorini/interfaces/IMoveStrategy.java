package interfaces;

public interface IMoveStrategy {
    boolean isValidMove(Worker worker, Tile fromTile, Tile toTile, Board board);
    void performMove(Worker worker, Tile toTile, Board board);
    boolean hasAdditionalMove();
}
