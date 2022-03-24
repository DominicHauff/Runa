package runasstrive.controller.gameStates.afterfight;

import runasstrive.controller.gameStates.GameState;
import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public class ChooseNewCards extends GameState {
    private static final IntegerParameter CHOICE = new IntegerParameter();
    private static final List<Parameter<?>> PARAMETER_LIST = List.of(CHOICE);

    protected ChooseNewCards(RunasStrive runasStrive) {
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
}
