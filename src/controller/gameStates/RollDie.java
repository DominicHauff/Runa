package controller.gameStates;

import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public class RollDie extends GameState {
    protected RollDie(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return null;
    }

    @Override
    public String repeatPrompt() {
        return null;
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return null;
    }

    @Override
    public Class<? extends GameState> getNext() {
        return null;
    }
}
