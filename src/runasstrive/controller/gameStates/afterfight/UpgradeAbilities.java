package runasstrive.controller.gameStates.afterfight;

import runasstrive.controller.gameStates.GameState;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public class UpgradeAbilities extends GameState {
    public UpgradeAbilities(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        //TODO: implement
        return null;
    }

    @Override
    public String repeatPrompt() {
        //TODO: implement
        return null;
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        //TODO: implement
        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        //TODO: implement
        return null;
    }
}
