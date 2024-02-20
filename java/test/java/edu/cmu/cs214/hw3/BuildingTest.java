public class Building {
    protected int level;

    public Building() {
        this.level = 0;
    }

    public int getLevel() {
        return level;
    }

    public void build() {
        if (level <= 3) this.level++;
    }

    public boolean isBuildable() {
        return level <= 3;
    }
}
