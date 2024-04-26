package components;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import godcards.Demeter;
import godcards.Hephaestus;
import godcards.Minotaur;
import godcards.Pan;

import interfaces.IBuildStrategy;
import interfaces.IWinStrategy;
import interfaces.IMoveStrategy;

public class GodCards {
    private Map<String, IBuildStrategy> buildStrategies = new HashMap<>();
    private Map<String, IMoveStrategy> moveStrategies = new HashMap<>();
    private Map<String, IWinStrategy> winStrategies = new HashMap<>();

    public GodCards() {
        registerBuildCard("Demeter", new Demeter());
        registerBuildCard("Hepheastus", new Hephaestus());
        registerMoveCard("Minotaur", new Minotaur());
        registerWinCard("Pan", new Pan());
    }

    public void registerBuildCard(String name, IBuildStrategy strategy) {
        buildStrategies.put(name, strategy);
    }

    public void registerMoveCard(String name, IMoveStrategy strategy) {
        moveStrategies.put(name, strategy);
    }

    public void registerWinCard(String name, IWinStrategy strategy) {
        winStrategies.put(name, strategy);
    }

    public IBuildStrategy getBuildStrategy(String name) {
        return buildStrategies.get(name);
    }

    public IMoveStrategy getMoveStrategy(String name) {
        return moveStrategies.get(name);
    }

    public IWinStrategy getWinStrategy(String name) {
        return winStrategies.get(name);
    }

    public Map<String, IBuildStrategy> getBuildStrategies() {
        return buildStrategies;
    }

    public Map<String, IMoveStrategy> getMoveStrategies() {
        return moveStrategies;
    }

    public Map<String, IWinStrategy> getWinStrategies() {
        return winStrategies;
    }

    public boolean isValidSelection(String godCardName) {
        return buildStrategies.containsKey(godCardName) ||
               moveStrategies.containsKey(godCardName) ||
               winStrategies.containsKey(godCardName);
    }
}
