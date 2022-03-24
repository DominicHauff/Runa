package runasstrive.controller.gameStates.fight;

import runasstrive.controller.gameStates.GameState;
import runasstrive.controller.gameStates.afterfight.ChooseReward;
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
        this.runasStrive.startFight();
        this.response = this.runasStrive.getFightLog();
        if (this.runasStrive.gameOver()) {
            this.response += System.lineSeparator() + Messages.ENTITY_DIES + System.lineSeparator();
            this.nextGameState = null;
        } else if (this.runasStrive.gameWon()) {
            this.response += System.lineSeparator() + Messages.GAME_WON + System.lineSeparator();
            this.nextGameState = null;
        } else if (this.runasStrive.isLevelCleared()) {
            this.nextGameState = ChooseReward.class;
        } else {
            this.nextGameState = ChooseAbility.class;
        }
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }

}
