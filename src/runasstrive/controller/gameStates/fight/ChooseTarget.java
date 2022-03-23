package runasstrive.controller.gamestates.fight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.List;
import java.util.stream.IntStream;

public class ChooseTarget extends GameState {
    protected ChooseTarget(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        StringBuilder targetStringBuilder = new StringBuilder();
        targetStringBuilder.append(Messages.SELECT_TARGET_MESSAGE).append(System.lineSeparator());
        IntStream.range(0, this.runasStrive.getPossibleTargets().size())
                .forEach(i -> {
                    targetStringBuilder.append(String.format(Messages.LIST_ELEMENT,
                            i + 1, this.runasStrive.getPossibleTargets().get(i)));
                });
        targetStringBuilder.append(this.repeatPrompt());
        return targetStringBuilder.toString();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPossibleTargets().size());
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return null;
    }

    @Override
    public Class<? extends GameState> getNext() {
        return null;
    }
}
