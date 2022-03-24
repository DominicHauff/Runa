package runasstrive.controller.gameStates;

import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public abstract class GameState {
    protected final RunasStrive runasStrive;
    protected String response;
    protected Class<? extends GameState> nextGameState;

    protected GameState(RunasStrive runasStrive) {
        this.runasStrive = runasStrive;
    }

    public String getResponse() {
        return this.response;
    }

    public Class<? extends GameState> getNext() {
        return this.nextGameState;
    }

    public abstract String getPrompt();

    public abstract String repeatPrompt();

    public abstract boolean execute(ParameterBundle parameterBundle);

    public abstract List<Parameter<?>> getParameters();

}
