public class Board {
    private TileTest[][] tiles;

    /**
     * Initializes an empty board
     */
    public Board() {
        tiles = new TileTest[5][5]; //5x5 board
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new TileTest(new Position(x, y));
            }
        }
    }

    /**
     * Resets the board
     */
    public void reset() {
        for (TileTest[] row : tiles) {
            for (TileTest tile : row) {
                tile.reset();
            }
        }
    }

    /**
     * Retrieves the tile at the specified coordinates.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile at the given coordinates, or null if the coordinates are out of bounds.
     */
    public Tile getTileAtPosition(int x, int y) {
        if (x >= 0 && x < tiles.length && y >= 0 && y < tiles[x].length) {
            return tiles[x][y];
        } else {
            return null;
        }
    }
}
