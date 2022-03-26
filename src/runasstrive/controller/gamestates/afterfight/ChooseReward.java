package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.dice.Die;

import java.util.List;

public class ChooseReward extends GameState {
    private static final IntegerParameter CHOICE = new IntegerParameter();
    private static final int NEW_CARDS = 1;
    private static final int NEW_DIE = 2;
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public ChooseReward(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        //TODO: remove magic number
        return String.format(Messages.ENTER_NUMBER_PROMPT, 2) + System.lineSeparator();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (!parameterBundle.isPresent(CHOICE)) {
            return false;
        }
        final int choice = parameterBundle.get(CHOICE);
        switch (choice) {
            case NEW_CARDS -> {
                this.nextGameState = ChooseNewCards.class;
                this.response = null;
            }
            case NEW_DIE -> {
                final Die die = this.runasStrive.upgradeDie();
                this.runasStrive.advanceToNextStage();
                this.response = String.format(Messages.UPGRADE_DIE, die.toString()) + System.lineSeparator();
                this.response += String.format(Messages.STAGE_ENTER_MESSAGE,
                        this.runasStrive.getCurrentLevel().getCurrentStage().getStageNumber(),
                        this.runasStrive.getCurrentLevel().getLevel().getValue()) + System.lineSeparator();
                this.nextGameState = this.runasStrive.canPlayerHeal() ? Heal.class : ChooseAbility.class;
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
