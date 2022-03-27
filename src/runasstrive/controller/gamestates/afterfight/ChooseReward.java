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
    public Parameter<?> getParameter() {
        return CHOICE;
    }

    /**
     * This method performs the interaction with {@link RunasStrive} using the given input
     * parameters.
     *
     * @param parameterBundle holds all required parameters for the game state
     * @return a boolean based on whether the interaction with the given input was performed
     * successfully
     */
    @Override
    protected boolean interact(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE);
        if (choice == NEW_DIE) {
            this.runasStrive.upgradeDie();
            this.nextGameState = this.runasStrive.canPlayerHeal() ? Heal.class : ChooseAbility.class;
        }
        else this.nextGameState = ChooseNewCards.class;
        return true;
    }

    /**
     * This method sets the next game state based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setNextGameState() {

    }

    /**
     * This method sets the response based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setResponse() {
        if (this.nextGameState == ChooseNewCards.class) {
            this.response = null;
            return;
        }
        final Die die = this.runasStrive.getCurrentDie();
        this.response = String.format(Messages.UPGRADE_DIE, die.toString());
        if (this.nextGameState == ChooseAbility.class) {
            this.response += System.lineSeparator() + String.format(Messages.STAGE_ENTER_MESSAGE,
                    this.runasStrive.getCurrentLevel().getCurrentStage().getStageNumber(),
                    this.runasStrive.getCurrentLevel().getLevel().getValue());
        }
    }
}
