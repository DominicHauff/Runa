package runasstrive.controller;

import runasstrive.controller.gameStates.GameState;
import runasstrive.model.RunasStrive;

import java.util.HashMap;
import java.util.Map;

public class GameStateSupplier {
    private final Map<Class<? extends GameState>, GameState> gameStates;

    public GameStateSupplier(RunasStrive runasStrive) {
        this.gameStates = new HashMap<>();
    }

    public Map<Class<? extends GameState>, GameState> getGameStates() {
        return this.gameStates;
    }
}
