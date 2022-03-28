package runasstrive.controller.gamestates.fight;

import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;

/**
 * This class represents the {@link FightGameState} in which
 * the player wants to play an ability that requires a die roll
 * and has to enter the result for additional damage.
 *
 * @author ugget
 * @version 1.0
 */
public class RollDie extends FightGameState {
    private static final SingleChoiceParameter DIE_RES = new SingleChoiceParameter();

    /**
     * This method constructs a new RollDie object.
     *
     * @param runasStrive the instance of the {@link RunasStrive} object used by all
     *                    game states
     */
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
        final int dieRes = parameterBundle.get(DIE_RES);
        return this.runasStrive.rollDie(dieRes);
    }

    /**
     * This method sets the next game state based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setNextGameState() {
        this.nextGameState = null;
    }

    /**
     * This method sets the response based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setResponse() {
        this.response = null;
    }

}
