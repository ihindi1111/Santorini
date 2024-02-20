public class Building {
    protected int level;

    /**
     * Initalizes new Building at a height of 1
     */
    public Building() {
        this.level = 1;
    }

    /**
     * Returns the current Level
     * @return int level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Reutrns true if the tower has not reached a max height yet
     */
    public boolean isBuildable() {
        return level <= 3;
    }
}
