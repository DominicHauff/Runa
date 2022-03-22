package controller;

import controller.gameStates.GameState;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.LinkedList;
import java.util.List;

public class Controller {
    private final RunasStrive runasStrive;
    private final GameStateSupplier gameStateSupplier;
    private final LinkedList<GameState> gameStates;
    private GameState currentGameState;

    public Controller(RunasStrive runasStrive, GameStateSupplier gameStateSupplier) {
        this.runasStrive = runasStrive;
        this.gameStateSupplier = gameStateSupplier;
        this.gameStates = gameStateSupplier.getGameStates();
        this.setNextGameState();
    }

    public String getPrompt() {
        return this.currentGameState.getPrompt();
    }

    public String interact(List<String> argumentList) {
        ParameterBundle bundle = new ParameterBundle();
        for (int i = 0; i < currentGameState.getParameters().size(); i++) {
            Parameter<?> parameter = this.currentGameState.getParameters().get(i);
            try {
                bundle.put(parameter, parameter.get(argumentList.get(i)));
            } catch (IllegalStateException | NullPointerException e) {
                return null;
            }
        }
        if (this.currentGameState.execute(bundle)) {
            String response = currentGameState.getResponse();
            this.setNextGameState();
            return response;
        }
        return null;
    }

    private void setNextGameState() {
        if (this.currentGameState == null) {
            this.currentGameState = this.gameStates.getFirst();
            this.gameStates.remove(currentGameState);
        } else {
            this.gameStates.remove(currentGameState);
            this.gameStates.addLast(currentGameState);
            this.currentGameState = this.gameStates.getFirst();
        }
    }
}
