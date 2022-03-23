package controller;

import controller.gameStates.GameState;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Controller {
    private final RunasStrive runasStrive;
    private final GameStateSupplier gameStateSupplier;
    private final Map<Class<? extends GameState>, GameState> gameStates;
    private GameState currentGameState;
    private boolean lastInputFaulty;

    public Controller(RunasStrive runasStrive, GameStateSupplier gameStateSupplier) {
        this.runasStrive = runasStrive;
        this.gameStateSupplier = gameStateSupplier;
        this.gameStates = gameStateSupplier.getGameStates();
        this.lastInputFaulty = false;
    }

    public String getPrompt() {
        return this.lastInputFaulty ? this.currentGameState.repeatPrompt() : this.currentGameState.getPrompt();
    }

    public String interact(List<String> argumentList) {
        ParameterBundle bundle = new ParameterBundle();
        if (argumentList.size() != this.currentGameState.getParameters().size()) {
            return null;
        }
        for (int i = 0; i < currentGameState.getParameters().size(); i++) {
            Parameter<?> parameter = this.currentGameState.getParameters().get(i);
            try {
                bundle.put(parameter, parameter.get(argumentList.get(i)));
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        if (this.currentGameState.execute(bundle)) {
            String response = currentGameState.getResponse();
            //TODO: find next state
            this.lastInputFaulty = false;
            return response;
        }
        this.lastInputFaulty = true;
        return null;
    }
}