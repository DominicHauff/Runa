package runasstrive.controller.gamestates.fight;

import runasstrive.io.parameters.SingleChoiceParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.List;

public class ChooseAbility extends FightGameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final int MIN_INDEX = 0;
    private static final int INDEX_OFFSET = 1;
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public ChooseAbility(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        //TODO: clean up
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(System.lineSeparator())
                .append(Messages.SEPARATOR).append(System.lineSeparator())
                .append(this.runasStrive.getPlayer().toString()).append(System.lineSeparator())
                .append(Messages.VS).append(System.lineSeparator());
        StringBuilder monsterStringBuilder = new StringBuilder();
        this.runasStrive.getCurrentLevel().getCurrentStage().getAliveMonsters().forEach(monster ->
                monsterStringBuilder.append(monster.toString()).append(System.lineSeparator()));
        stringBuilder
                .append(monsterStringBuilder)
                .append(Messages.SEPARATOR).append(System.lineSeparator());
        List<Ability> abilities = this.runasStrive.getPlayer().getAbilities();

        stringBuilder.append(Messages.SELECT_CARD_MESSAGE).append(System.lineSeparator());
        for (int i = 0; i < abilities.size(); i++) {
            stringBuilder
                    //TODO: remove magic number
                    .append(String.format(Messages.LIST_ELEMENT, i + 1, abilities.get(i).toString()))
                    .append(System.lineSeparator());
        }

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

        this.response = String.format(Messages.ENTITY_USES_ABILITY, this.runasStrive.getPlayer().getName(), card)
                + System.lineSeparator();

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

        this.startFight();
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
