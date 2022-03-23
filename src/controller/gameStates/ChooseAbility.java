package controller.gameStates;

import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.List;

public class ChooseAbility extends GameState {
    private static final IntegerParameter CHOICE = new IntegerParameter();
    private static final List<Parameter<?>> PARAMETER_LIST = List.of(CHOICE);
    private Class<? extends GameState> nextGameState;

    protected ChooseAbility(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(Messages.SEPARATOR).append(System.lineSeparator())
                .append(this.runasStrive.getPlayer().toString()).append(System.lineSeparator())
                .append(Messages.VS).append(System.lineSeparator());
        StringBuilder monsterStringBuilder = new StringBuilder();
        this.runasStrive.getCurrentLevel().getCurrentStage().getMonsters().forEach(monster ->
                monsterStringBuilder.append(monster.toString()).append(System.lineSeparator()));
        stringBuilder
                .append(monsterStringBuilder.toString()).append(System.lineSeparator())
                .append(Messages.SEPARATOR).append(System.lineSeparator())
                .append(this.repeatPrompt());
        return stringBuilder.toString();
    }

    @Override
    public String repeatPrompt() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Ability> abilities = this.runasStrive.getPlayer().getAbilities();
        stringBuilder
                .append(String.format(Messages.ENTER_NUMBER_PROMPT, abilities.size())).append(System.lineSeparator());
        for (int i = 0; i < abilities.size(); i++) {
            stringBuilder
                    //TODO: remove magic number
                    .append(String.format(Messages.ABILITY_LIST_ELEMENT, i + 1, abilities.get(i).toString()))
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE);
        final Ability card = this.runasStrive.pickCard(choice);
        if (card == null) return false;
        if (!card.targetRequired()) {
            this.nextGameState = UseAbility.class;
            return true;
        }
        if (this.runasStrive.requiresTargetChoice()) {
            this.nextGameState = ChooseTarget.class;
            return true;
        }
        if (card.dieRollRequired()) {
            this.nextGameState = RollDie.class;
            return true;
        }
        this.nextGameState = UseAbility.class;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETER_LIST;
    }

    @Override
    public Class<? extends GameState> getNext() {
        return this.nextGameState;
    }

}