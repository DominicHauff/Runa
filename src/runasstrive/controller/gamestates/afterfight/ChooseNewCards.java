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
        StringBuilder builder = new StringBuilder();
        builder
                .append(String.format(Messages.PICK_CARD_PROMPT, this.runasStrive.getNumRewardCards()))
                .append(System.lineSeparator());

        IntStream.range(0, this.runasStrive.getRewards().size()).forEach(i -> builder
                .append(String.format(Messages.LIST_ELEMENT, i + CARD_INDEX_OFFSET, this.runasStrive.getRewards().get(i)))
                .append(System.lineSeparator())
        );
        return builder + this.repeatPrompt();
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
}
