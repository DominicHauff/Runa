package runasstrive.controller.gamestates.fight;

import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;

public class RollDie extends FightGameState {
    private static final SingleChoiceParameter DIE_RES = new SingleChoiceParameter();

    public RollDie(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    protected void interactAfterFight() {

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
        this.response = null;
        this.startFight();
        return true;
    }

    @Override
    public Parameter<?> getParameter() {
        return DIE_RES;
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
        return false;
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

    }

}
