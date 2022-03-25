package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.List;
import java.util.stream.IntStream;

public class Heal extends GameState {
    private static final CardIndexParameter CHOICE = new CardIndexParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public Heal(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(Messages.HEALING_OPTION, this.runasStrive.getPlayer().getHp())).append(System.lineSeparator());
        IntStream.range(0, this.runasStrive.getPlayer().getAbilities().size()).forEach(i -> {
            builder.append(String.format(Messages.LIST_ELEMENT, i + 1,
                    this.runasStrive.getPlayer().getAbilities().get(i).toString())).append(System.lineSeparator());
        });
        return builder.toString() + this.repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPlayer().getAbilities().size());
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (parameterBundle.isPresent(CHOICE)) {
            final int choice = parameterBundle.get(CHOICE);
            if (this.runasStrive.healPlayer(choice)) {
                this.nextGameState = InitializeLevel.class;
                return true;
            }
            return false;
        }
        this.response = null;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
