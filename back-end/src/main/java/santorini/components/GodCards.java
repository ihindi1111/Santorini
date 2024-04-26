package components;

import interfaces.GodStrategy;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class GodCards {
    private Map<String, GodStrategy> strategies = new HashMap<>();

    public void registerGodCard(String name, GodStrategy strategy) {
        strategies.put(name, strategy);
    }

    public GodStrategy getStrategy(String name) {
        return strategies.get(name);
    }

    public boolean isValidSelection(String godCardName) {
        return strategies.containsKey(godCardName);
    }

    // Method to get a god card name based on an index safely
    public String getCardAtPosition(int index) {
        ArrayList<String> keys = new ArrayList<>(strategies.keySet());
        if (index >= 0 && index < keys.size()) {
            return keys.get(index);
        }
        return null; // Return null if the index is out of range
    }
}