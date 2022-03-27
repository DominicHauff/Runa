package runasstrive.controller.gamestates;

import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

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

    public abstract Parameter<?> getParameter();

}
