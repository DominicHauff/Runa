package runasstrive.controller.gameStates.afterfight;

import runasstrive.controller.gameStates.GameState;
import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.List;

public class ChooseReward extends GameState {
    private static final IntegerParameter CHOICE = new IntegerParameter();
    private static final List<Parameter<?>> PARAMETER_LIST = List.of(CHOICE);

    public ChooseReward(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        if (this.runasStrive.canChooseDie()) {
            StringBuilder builder = new StringBuilder();
            builder.append(Messages.CHOOSE_RUNAS_REWARD).append(System.lineSeparator())
                    .append(Messages.CHOOSE_NEW_ABILITIES).append(System.lineSeparator())
                    .append(Messages.CHOOSE_NEW_DIE).append(System.lineSeparator());
            builder.append(this.repeatPrompt());
            return builder.toString();
        }
        this.nextGameState = ChooseNewCards.class;
        return null;
    }

    @Override
    public String repeatPrompt() {
        //TODO: remove magic number
        return String.format(Messages.ENTER_NUMBER_PROMPT, 2);
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (this.getPrompt() != null) {
            //TODO: remove magic number
            if (parameterBundle.get(CHOICE) == 2) {

            }
        }
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        //TODO: implement
        return null;
    }
}
