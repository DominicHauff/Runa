package controller;

import controller.gameStates.GameState;
import controller.gameStates.InitializeLevel;
import runasstrive.model.RunasStrive;

import java.util.List;

public class GameStateSupplier {
    private final List<GameState> gameStates;

    public GameStateSupplier(RunasStrive runasStrive) {
        this.gameStates = List.of(
                new InitializeLevel(runasStrive)
        );
    }

    public List<GameState> getGameStates() {
        return gameStates;
    }
}
