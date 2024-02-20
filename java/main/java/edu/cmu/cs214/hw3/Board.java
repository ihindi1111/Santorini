public class Board {
    private Tile[][] tiles;

    public Board() {
        tiles = new Tile[5][5]; //5x5 board
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y] = new Tile(new Position(x, y));
            }
        }
    }

    public void reset() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.reset();
            }
        }
    }
}
