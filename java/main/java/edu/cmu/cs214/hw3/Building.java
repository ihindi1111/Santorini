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
     * Builds if the tower has not reached a max height yet
     */
    public void build() throws MaxLevelReachedException {
        if (level <= 3) {
            this.level++;
        } else {
            throw new MaxLevelReachedException("Cannot build beyond the maximum level.");
        }
    }
}
