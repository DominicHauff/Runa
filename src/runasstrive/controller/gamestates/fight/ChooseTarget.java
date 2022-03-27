package runasstrive.controller.gamestates.fight;

import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.stream.IntStream;

public class ChooseTarget extends FightGameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final int INDEX_OFFSET = 1;
    private static final int MIN_INDEX = 0;

    public ChooseTarget(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        StringBuilder targetStringBuilder = new StringBuilder();
        targetStringBuilder.append(Messages.SELECT_TARGET_MESSAGE).append(System.lineSeparator());

        IntStream.range(0, this.runasStrive.getPossibleTargets().size())
                .forEach(i -> targetStringBuilder
                        .append(String.format(
                                Messages.LIST_ELEMENT, i + 1, this.runasStrive.getPossibleTargets().get(i).getName()))
                        .append(System.lineSeparator()));

        return targetStringBuilder + repeatPrompt();
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

}
