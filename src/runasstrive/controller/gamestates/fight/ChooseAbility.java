package runasstrive.controller.gamestates.fight;

import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

public class ChooseAbility extends FightGameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final int MIN_INDEX = 0;
    private static final int INDEX_OFFSET = 1;

    public ChooseAbility(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        String stringBuilder = Messages.SEPARATOR + System.lineSeparator()
                + this.runasStrive.getPlayer().toString() + System.lineSeparator()
                + Messages.VS + System.lineSeparator()
                + this.runasStrive.getCurrentLevel().getCurrentStage().toString() + System.lineSeparator()
                + Messages.SEPARATOR + System.lineSeparator()
                + Messages.SELECT_CARD_MESSAGE + System.lineSeparator()
                + this.list(this.runasStrive.getPlayer().getAbilities(), INDEX_OFFSET) + System.lineSeparator();
        return stringBuilder + repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPlayer().getAbilities().size());
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
        return this.runasStrive.pickCard(choice);
    }

    /**
     * This method sets the next game state based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setNextGameState() {
        final Ability card = this.runasStrive.getPlayer().getCardToPlay();
        if (card.targetRequired() && this.runasStrive.requiresTargetChoice()) {
            this.nextGameState = ChooseTarget.class;
        } else if (card.dieRollRequired()) {
            this.nextGameState = RollDie.class;
        } else {
            this.nextGameState = null;
        }
    }

    /**
     * This method sets the response based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setResponse() {
        final Ability card = this.runasStrive.getCardToPlay();
        if (this.nextGameState == ChooseTarget.class) {
            this.response = null;
            return;
        }
        this.response = String.format(Messages.ENTITY_USES_ABILITY, this.runasStrive.getPlayer().getName(), card);
        if (this.nextGameState == null) {
            this.response += System.lineSeparator();
        }
    }

    @Override
    public Parameter<?> getParameter() {
        return CHOICE;
    }
}
