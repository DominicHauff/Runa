package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.dice.Die;

public class ChooseReward extends GameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final int NEW_CARDS = 1;
    private static final int NEW_DIE = 2;
    private static final int NUM_CHOICES = 2;

    public ChooseReward(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, NUM_CHOICES);
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (parameterBundle.get(CHOICE) == null) {
            return false;
        }
        final int choice = parameterBundle.get(CHOICE);
        switch (choice) {
            case NEW_CARDS:
                this.nextGameState = ChooseNewCards.class;
                this.response = null;
                break;
            case NEW_DIE:
                final Die die = this.runasStrive.upgradeDie();
                this.response = String.format(Messages.UPGRADE_DIE, die.toString());
                this.nextGameState = this.runasStrive.canPlayerHeal() ? Heal.class : ChooseAbility.class;
                if (this.nextGameState == ChooseAbility.class) {
                    this.response += System.lineSeparator() + String.format(Messages.STAGE_ENTER_MESSAGE,
                            this.runasStrive.getCurrentLevel().getCurrentStage().getStageNumber(),
                            this.runasStrive.getCurrentLevel().getLevel().getValue());
                }
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public Parameter<?> getParameter() {
        return CHOICE;
    }
}
