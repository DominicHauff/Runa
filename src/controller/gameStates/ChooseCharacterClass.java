package controller.gameStates;

import runasstrive.io.parameters.IntegerParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.entity.type.CharacterType;

import java.util.List;

public class ChooseCharacterClass extends GameState {
    private static final IntegerParameter CHOICE = new IntegerParameter();
    private static final List<Parameter<?>> PARAMETER_LIST = List.of(CHOICE);

    public ChooseCharacterClass(RunasStrive runasStrive) {
        super(runasStrive);
        this.response = null;
    }

    @Override
    public String getPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, CharacterType.values().length);
    }

    @Override
    public String repeatPrompt() {
        return getPrompt();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE);
        CharacterType type = CharacterType.getCharacterType(choice);
        if (type == null) return false;
        this.runasStrive.getPlayer().setType(type);
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETER_LIST;
    }
}
