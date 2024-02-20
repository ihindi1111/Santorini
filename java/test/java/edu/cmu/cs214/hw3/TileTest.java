public class Tile {
    private Position position;
    private boolean isAvailable;
    private Building hasBuilding;
    private boolean isOccupied;

    /**
     * Creates new Tile, holding the position, if available, if occupied by another player
     * @param position
     */
    public Tile(Position position) {
        this.position = position;
        this.isAvailable = true;
        this.hasBuilding = null;
        this.isOccupied = false;
    }

    public void reset() {
        this.isAvailable = true;
        this.hasBuilding = null;
        this.isOccupied = false;
    }

    public boolean isOpen(Tile t) {
        if (!t.getLevel().isBuildable()) isAvailable = false;
        return isAvailable;
    }
}
