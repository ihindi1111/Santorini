public class Tile {
    private Position position;
    private boolean isAvailable;
    private Building hasBuilding;
    private boolean isOccupied;

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
