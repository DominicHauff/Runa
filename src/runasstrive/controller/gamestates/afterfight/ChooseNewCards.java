package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class ChooseNewCards extends GameState {
    private static final CardIndexParameter FIRST = new CardIndexParameter();
    private static final CardIndexParameter SECOND = new CardIndexParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(FIRST, SECOND);

    public ChooseNewCards(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(Messages.PICK_CARD_PROMPT, this.runasStrive.getNumRewardCards()))
                .append(System.lineSeparator());

        IntStream.range(0, this.runasStrive.getRewards().size()).forEach(i ->
                builder.append(String.format(Messages.LIST_ELEMENT, i + 1, this.runasStrive.getRewards().get(i)))
                        .append(System.lineSeparator())
        );
        return builder + this.repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        final String prompt = this.runasStrive.getNumRewardCards() > 1
                ? String.format(Messages.MULTIPLE_CARDS_PROMPT, this.runasStrive.getRewards().size())
                : String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getRewards().size());
        return prompt + System.lineSeparator();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        this.runasStrive.advanceToNextStage();

        if (!parameterBundle.isPresent(FIRST)) {
            return false;
        }
        final int firstCardIndex = parameterBundle.get(FIRST);
        if (this.runasStrive.getRewards().size() <= firstCardIndex) {
            return false;
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
        this.response = rewardBuilder.toString();
        this.nextGameState  = this.runasStrive.canPlayerHeal() ? Heal.class : ChooseAbility.class;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
