package controller.gameStates;

import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public abstract class OptionalGameState extends GameState {
    protected boolean isActive;

    protected OptionalGameState(RunasStrive runasStrive) {
        super(runasStrive);
        this.isActive = false;
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        this.deactivate();
        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return null;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }
}
