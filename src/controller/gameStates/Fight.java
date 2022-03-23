package controller.gameStates;

import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.List;

public class Fight extends GameState {
    protected Fight(RunasStrive runasStrive) {
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
        return null;
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return null;
    }
}
