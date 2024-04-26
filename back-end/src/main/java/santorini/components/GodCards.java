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

    // Method to get a god card name based on an index
    public String getCardAtPosition(int index) {
        return new ArrayList<>(strategies.keySet()).get(index);
    }
}