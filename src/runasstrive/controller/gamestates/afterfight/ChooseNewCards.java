package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.view.parameters.MultipleChoiceParameter;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class ChooseNewCards extends GameState {
    private static final MultipleChoiceParameter CHOICES = new MultipleChoiceParameter();
    private static final int EXPECTED_MIN_SIZE = 1;
    private static final int CARD_INDEX_OFFSET = 1;

    public ChooseNewCards(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return  String.format(Messages.PICK_CARD_PROMPT, this.runasStrive.getNumRewardCards())
                + System.lineSeparator() + this.list(this.runasStrive.getRewards(), CARD_INDEX_OFFSET)
                + System.lineSeparator() + this.repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return this.runasStrive.getNumRewardCards() > EXPECTED_MIN_SIZE
                ? String.format(Messages.MULTIPLE_CARDS_PROMPT, this.runasStrive.getRewards().size())
                : String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getRewards().size());
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final List<Integer> choices = parameterBundle.get(CHOICES);
        final Set<Integer> choiceSet = new HashSet<>(choices);

        if (choices.size() != choiceSet.size()) {
            return false;
        }

        if (choices.size() < EXPECTED_MIN_SIZE || choices.size() != this.runasStrive.getNumRewardCards()) {
            return false;
        }

        for (Integer choice : choices) {
            if (choice > this.runasStrive.getRewards().size()) return false;
        }

        LinkedList<Ability> reward = new LinkedList<>(); //TODO: find better solution if everything else works fine
        for (Integer choice : choices) {
            final Ability card = this.runasStrive.drawCard(choice - CARD_INDEX_OFFSET); //TODO: fix error;
            reward.add(card);
        }

        this.runasStrive.discardLeftOverReward();
        StringBuilder rewardBuilder = new StringBuilder();
        reward.forEach(ability -> rewardBuilder.append(String.format(Messages.GET_NEW_CARD, ability.toString()))
                .append(System.lineSeparator()));

        this.nextGameState  = this.runasStrive.canPlayerHeal() ? Heal.class : ChooseAbility.class;
        this.response = this.nextGameState == Heal.class ? rewardBuilder.toString().trim() : rewardBuilder
                + String.format(Messages.STAGE_ENTER_MESSAGE,
                this.runasStrive.getCurrentLevel().getCurrentStage().getStageNumber(),
                this.runasStrive.getCurrentLevel().getLevel().getValue());

        return true;
    }

    @Override
    public Parameter<?> getParameter() {
        return CHOICES;
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
