package controller;

import controller.gameStates.GameState;
import runasstrive.model.RunasStrive;

import java.util.List;

public class Controller {
    private final RunasStrive runasStrive;
    private final GameStateSupplier gameStateSupplier;
    private final List<GameState> gameStates;
    private GameState currentGameState;

    public Controller(RunasStrive runasStrive, GameStateSupplier gameStateSupplier) {
        this.runasStrive = runasStrive;
        this.gameStateSupplier = gameStateSupplier;
        this.gameStates = gameStateSupplier.getGameStates();
    }

    public String interact(List<String> argumentList) {
        return currentGameState.getResponse();
    }
}
