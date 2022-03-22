package controller.gameStates;

import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.List;

public class ChooseCharacterClass extends GameState {
    private static final IntegerParameter CHOICE = new IntegerParameter();
    private static final List<Parameter<?>> PARAMETER_LIST = List.of(CHOICE);

    protected ChooseCharacterClass(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        //TODO: call appropriate methods
        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETER_LIST;
    }
}
