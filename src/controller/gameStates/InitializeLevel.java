package controller.gameStates;

import controller.gameStates.GameState;
import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public class InitializeLevel extends GameState {
    private static final IntegerParameter FIRST_SEED = new IntegerParameter();
    private static final IntegerParameter SECOND_SEED = new IntegerParameter();
    private static final List<Parameter<?>> PARAMETER_LIST = List.of(FIRST_SEED, SECOND_SEED);

    public InitializeLevel(RunasStrive runasStrive) {
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
