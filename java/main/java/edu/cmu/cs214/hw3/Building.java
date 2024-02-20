public class BuildingTest {
    protected int level;

    public BuildingTest() {
        this.level = 0;
    }

    public int getLevel() {
        return level;
    }

    public void build() throws IllegalStateException {
        if (level <= 3) {
            this.level++;
        } else {
            throw new IllegalStateException("Cannot build beyond the maximum level.");
        }
    }
}
