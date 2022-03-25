package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.dice.Die;

import java.util.List;

public class ChooseReward extends GameState {
    private static final IntegerParameter CHOICE = new IntegerParameter();
    private static final int NEW_CARDS = 1;
    private static final int NEW_DIE = 2;
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public ChooseReward(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        String prompt = Messages.CHOOSE_RUNAS_REWARD + System.lineSeparator() +
                Messages.CHOOSE_NEW_ABILITIES_OPTION + System.lineSeparator() +
                Messages.CHOOSE_NEW_DIE + System.lineSeparator() +
                this.repeatPrompt();
        this.nextGameState = ChooseNewCards.class;
        return prompt;
    }

    @Override
    public String repeatPrompt() {
        //TODO: remove magic number
        return String.format(Messages.ENTER_NUMBER_PROMPT, 2);
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE);
        switch (choice) {
            case NEW_CARDS -> {
                this.nextGameState = ChooseNewCards.class;
            }
            case NEW_DIE -> {
                final Die die = this.runasStrive.upgradeDie();
                this.response = String.format(Messages.UPGRADE_DIE, die.toString());
            }
            default -> {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
