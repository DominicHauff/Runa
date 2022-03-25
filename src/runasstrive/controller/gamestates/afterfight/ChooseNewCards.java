package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.List;

public class ChooseNewCards extends GameState {
    private static final CardIndexParameter CHOICE = new CardIndexParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public ChooseNewCards(RunasStrive runasStrive) {
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
