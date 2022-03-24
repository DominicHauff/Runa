package runasstrive.controller.gamestates.fight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.afterfight.ChooseReward;
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

    public ChooseAbility(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        //TODO: clean up
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
                .append(Messages.SEPARATOR).append(System.lineSeparator());
        List<Ability> abilities = this.runasStrive.getPlayer().getAbilities();

        stringBuilder.append(Messages.SELECT_CARD_MESSAGE).append(System.lineSeparator());
        for (int i = 0; i < abilities.size(); i++) {
            stringBuilder
                    //TODO: remove magic number
                    .append(String.format(Messages.LIST_ELEMENT, i + 1, abilities.get(i).toString()))
                    .append(System.lineSeparator());
        }
        stringBuilder.append(this.repeatPrompt());
        return stringBuilder.toString();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT,
                this.runasStrive.getPlayer().getAbilities().size()) + System.lineSeparator();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE);
        final Ability card = this.runasStrive.pickCard(choice);
        if (card == null) return false;
        if (!card.targetRequired()) {
            this.runasStrive.startFight();
            this.response = this.runasStrive.getFightLog();
            if (this.runasStrive.gameOver()) {
                this.response += System.lineSeparator() + Messages.ENTITY_DIES + System.lineSeparator();
                this.nextGameState = null;
            } else if (this.runasStrive.gameWon()) {
                this.response += System.lineSeparator() + Messages.GAME_WON + System.lineSeparator();
                this.nextGameState = null;
            } else if (this.runasStrive.isLevelCleared()) {
                this.nextGameState = ChooseReward.class;
            } else {
                this.nextGameState = ChooseAbility.class;
            }
            return true;
        }
        if (this.runasStrive.requiresTargetChoice()) {
            this.nextGameState = ChooseTarget.class;
            this.response = null;
            return true;
        }
        if (card.dieRollRequired()) {
            this.nextGameState = RollDie.class;
            this.response = null;
            return true;
        }
        this.runasStrive.startFight();
        this.response = this.runasStrive.getFightLog();
        if (this.runasStrive.gameOver()) {
            this.response += System.lineSeparator() + Messages.ENTITY_DIES + System.lineSeparator();
            this.nextGameState = null;
        } else if (this.runasStrive.gameWon()) {
            this.response += System.lineSeparator() + Messages.GAME_WON + System.lineSeparator();
            this.nextGameState = null;
        } else if (this.runasStrive.isLevelCleared()) {
            this.nextGameState = ChooseReward.class;
        } else {
            this.nextGameState = ChooseAbility.class;
        }
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
