package controller;

import runasstrive.model.RunasStrive;

import java.util.List;

public class Controller {
    private final RunasStrive runasStrive;
    private final List<GameState> gameStates;
    private GameState currentGameState;

    public Controller(RunasStrive runasStrive, List<GameState> gameStates) {
        this.runasStrive = runasStrive;
        this.gameStates = gameStates;
    }

    public String interact(List<String> argumentList) {
        return currentGameState.getResponse();
    }
}
