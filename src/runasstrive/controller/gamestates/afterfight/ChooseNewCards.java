package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.LinkedList;
import java.util.List;

public class ChooseNewCards extends GameState {
    private static final CardIndexParameter FIRST = new CardIndexParameter();
    private static final CardIndexParameter SECOND = new CardIndexParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(FIRST, SECOND);

    public ChooseNewCards(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return String.format(Messages.PICK_CARD_PROMPT, this.runasStrive.getNumRewardCards()) + System.lineSeparator()
                + String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getRewards().size())
                + System.lineSeparator();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getRewards().size())
                + System.lineSeparator();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (!parameterBundle.isPresent(FIRST)) {
            return false;
        }
        LinkedList<Ability> reward = new LinkedList<>(); //TODO: find better solution if everything else works fine
        final int firstCardIndex = parameterBundle.get(FIRST);
        if (parameterBundle.isPresent(SECOND)) {
            if (PARAMETERS.size() != this.runasStrive.getNumRewardCards()) {
                return false;
            }
            final int secondCardIndex = parameterBundle.get(SECOND);
            if (secondCardIndex == firstCardIndex) {
                return false;
            }
            if (this.runasStrive.getRewards().size() <= secondCardIndex) {
                return false;
            }
            reward.addFirst(this.runasStrive.drawCard(secondCardIndex));
        }
        reward.addFirst(this.runasStrive.drawCard(firstCardIndex));
        if (this.runasStrive.getRewards().size() <= firstCardIndex) {
            return false;
        }
        this.runasStrive.discardLeftOverReward();
        StringBuilder rewardBuilder = new StringBuilder();
        reward.forEach(ability -> rewardBuilder.append(String.format(Messages.GET_NEW_CARD, ability.toString()))
                .append(System.lineSeparator()));
        this.response = rewardBuilder.toString();
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
