package controller;

import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public abstract class GameState {
    protected final RunasStrive runasStrive;
    protected String response;

    protected GameState(RunasStrive runasStrive) {
        this.runasStrive = runasStrive;
    }

    public String getResponse() {
        return response;
    }

    public abstract boolean execute(ParameterBundle parameterBundle);
    public abstract List<Parameter<?>> getParameters();
    public abstract String getOpening();
}
