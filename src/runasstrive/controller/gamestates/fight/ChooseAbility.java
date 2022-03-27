package runasstrive.controller.gamestates.fight;

import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.List;

public class ChooseAbility extends FightGameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final int MIN_INDEX = 0;
    private static final int INDEX_OFFSET = 1;

    public ChooseAbility(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(Messages.SEPARATOR).append(System.lineSeparator())
                .append(this.runasStrive.getPlayer().toString()).append(System.lineSeparator())
                .append(Messages.VS).append(System.lineSeparator())
                .append(this.runasStrive.getCurrentLevel().getCurrentStage().toString()).append(System.lineSeparator())
                .append(Messages.SEPARATOR).append(System.lineSeparator())
                .append(Messages.SELECT_CARD_MESSAGE).append(System.lineSeparator())
                .append(this.list(this.runasStrive.getPlayer().getAbilities(), INDEX_OFFSET))
                .append(System.lineSeparator());
        return stringBuilder + repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPlayer().getAbilities().size());
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE) - INDEX_OFFSET;
        if (choice < MIN_INDEX) {
            return false;
        }
        final Ability card = this.runasStrive.pickCard(choice);
        if (card == null) return false;

        this.response = String.format(Messages.ENTITY_USES_ABILITY, this.runasStrive.getPlayer().getName(), card);
        if (card.targetRequired()) {
            if (this.runasStrive.requiresTargetChoice()) {
                this.response = null;
                this.nextGameState = ChooseTarget.class;
                return true;
            } else if (card.dieRollRequired()) {
                this.nextGameState = RollDie.class;
                return true;
            }
        }
        this.response += System.lineSeparator();
        this.startFight();
        return true;
    }

    @Override
    public Parameter<?> getParameter() {
        return CHOICE;
    }
}
