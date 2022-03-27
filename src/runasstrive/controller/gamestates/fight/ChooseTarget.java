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
import java.util.stream.IntStream;

public class ChooseTarget extends FightGameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final int INDEX_OFFSET = 1;
    private static final int MIN_INDEX = 0;

    public ChooseTarget(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    protected void interactAfterFight() {

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
    public boolean execute(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE) - INDEX_OFFSET;
        if (choice < MIN_INDEX) {
            return false;
        }
        if (!this.runasStrive.pickTarget(choice)) return false;

        final Ability cardToPlay = this.runasStrive.getCardToPlay();
        this.response = String.format(Messages.ENTITY_USES_ABILITY, this.runasStrive.getPlayer().getName(), cardToPlay);

        if (!cardToPlay.dieRollRequired()) {
            this.response += System.lineSeparator();
            this.startFight();
            return true;
        }

        this.nextGameState = RollDie.class;
        return true;
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
