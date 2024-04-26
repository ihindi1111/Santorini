package components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GodCardManager {
    private List<String> godCardNames; // Assuming a list of god card names
    private Map<Integer, String> godCardPositions; // Maps position index to god card name

    public GodCardManager(List<String> godCardNames) {
        this.godCardNames = new ArrayList<>(godCardNames);
        this.godCardPositions = new HashMap<>();
        initializePositions();
    }

    private void initializePositions() {
        // Assume a simple sequential placement for simplicity
        int index = 0;
        for (String card : godCardNames) {
            godCardPositions.put(index, card);
            index++;
        }
    }

    public String getCardAtPosition(int x, int y, int width) {
        int index = y * width + x;
        return godCardPositions.getOrDefault(index, null);
    }
}
