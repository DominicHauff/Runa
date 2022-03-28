package runasstrive.controller.gamestates.fight;

import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the {@link FightGameState} in which
 * the player wants to play an ability that requires a target
 * and now has to choose which enemy to attack.
 *
 * @author ugget
 * @version 1.0
 */
public class ChooseTarget extends FightGameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final int INDEX_OFFSET = 1;
    private static final int MIN_INDEX = 0;

    /**
     * This method constructs a new ChooseTarget object.
     *
     * @param runasStrive the instance of the {@link RunasStrive} object used by all
     *                    game states
     */
    public ChooseTarget(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        final List<String> possibleTargets = this.runasStrive.getPossibleTargets().stream()
                .map(Monster::getName)
                .collect(Collectors.toList());

        String targets = Messages.SELECT_TARGET_MESSAGE + System.lineSeparator()
                + this.list(possibleTargets, INDEX_OFFSET) + System.lineSeparator();

        return targets + repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPossibleTargets().size());
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
        final int choice = parameterBundle.get(CHOICE) - INDEX_OFFSET;
        if (choice < MIN_INDEX) {
            return false;
        }
        return this.runasStrive.pickTarget(choice);
    }

    /**
     * This method sets the next game state based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setNextGameState() {
        final Ability cardToPlay = this.runasStrive.getCardToPlay();
        this.nextGameState = cardToPlay.dieRollRequired() ? RollDie.class : null;
    }

    /**
     * This method sets the response based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setResponse() {
        final Ability cardToPlay = this.runasStrive.getCardToPlay();
        this.response = String.format(Messages.ENTITY_USES_ABILITY, this.runasStrive.getPlayer().getName(), cardToPlay);
        if (this.nextGameState == null) {
            this.response += System.lineSeparator();
        }
    }

}
