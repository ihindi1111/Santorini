public class Building {
    protected int level;

    public Building() {
        this.level = 0; // Initial level
    }

    public int getLevel() {
        return level;
    }

    public boolean isBuildable() {
        return level <= 3;
    }
}
