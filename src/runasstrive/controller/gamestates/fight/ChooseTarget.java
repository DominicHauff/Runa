package runasstrive.controller.gamestates.fight;

import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.List;
import java.util.stream.IntStream;

public class ChooseTarget extends FightGameState {
    private static final CardIndexParameter CHOICE = new CardIndexParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public ChooseTarget(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        StringBuilder targetStringBuilder = new StringBuilder();
        targetStringBuilder.append(Messages.SELECT_TARGET_MESSAGE).append(System.lineSeparator());
        IntStream.range(0, this.runasStrive.getPossibleTargets().size())
                .forEach(i -> targetStringBuilder.append(String.format(
                        Messages.LIST_ELEMENT, i + 1, this.runasStrive.getPossibleTargets().get(i))));
        targetStringBuilder.append(this.repeatPrompt());
        return targetStringBuilder.toString();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPossibleTargets().size());
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (!parameterBundle.isPresent(CHOICE)) {
            return false;
        }
        final int choice = parameterBundle.get(CHOICE);

        if (!this.runasStrive.pickTarget(choice)) return false;

        final Ability cardToPlay = this.runasStrive.getCardToPlay();

        if (!cardToPlay.dieRollRequired()) {
            this.startFight();
            return true;
        }

        this.nextGameState = RollDie.class;
        this.response = null;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }

}
