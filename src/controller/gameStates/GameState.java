package controller.gameStates;

import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public abstract class GameState {
    protected final RunasStrive runasStrive;
    protected String response;
    protected String prompt;

    protected GameState(RunasStrive runasStrive) {
        this.runasStrive = runasStrive;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public String getResponse() {
        return this.response;
    }

    public abstract boolean execute(ParameterBundle parameterBundle);
    public abstract List<Parameter<?>> getParameters();
}
