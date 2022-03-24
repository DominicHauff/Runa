package runasstrive.controller.gameStates.fight;

import runasstrive.controller.gameStates.GameState;
import runasstrive.controller.gameStates.afterfight.ChooseReward;
import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;

import java.util.List;
import java.util.stream.IntStream;

public class ChooseTarget extends GameState {
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
        final int choice = parameterBundle.get(CHOICE);
        if (!this.runasStrive.pickTarget(choice)) {
            return false;
        }
        final Ability cardToPlay = this.runasStrive.getCardToPlay();
        if (cardToPlay.dieRollRequired()) {
            this.nextGameState = RollDie.class;
            this.response = null;
        } else {
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
        }
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }

}
