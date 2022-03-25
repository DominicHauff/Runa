package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public class Heal extends GameState {
    private static final CardIndexParameter CHOICE = new CardIndexParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public Heal(RunasStrive runasStrive) {
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
        if (parameterBundle.isPresent(CHOICE)) {
            final int choice = parameterBundle.get(CHOICE);
            if (this.runasStrive.healPlayer(choice)) {
                this.nextGameState = InitializeLevel.class;
                return true;
            }
            return false;
        }
        this.response = null;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
