package runasstrive.controller.gamestates.fight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.List;

public class RollDie extends GameState {
    private static final IntegerParameter DIE_RES = new IntegerParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(DIE_RES);

    public RollDie(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return String.format(Messages.ENTER_DICE_ROLL_PROMPT, this.runasStrive.getCurrentDie().getSides());
    }

    @Override
    public String repeatPrompt() {
        return getPrompt();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final int dieRes = parameterBundle.get(DIE_RES);
        if (!this.runasStrive.rollDie(dieRes)) {
            return false;
        }
        this.nextGameState = UseAbility.class;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }

}
