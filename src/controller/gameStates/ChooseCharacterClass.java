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
    private static final int[] CHOICE_RANGE = {1, 3};

    public ChooseCharacterClass(RunasStrive runasStrive) {
        super(runasStrive);
        this.prompt = Messages.getEnterNumberPrompt(CHOICE_RANGE);
        this.response = null;
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        switch (parameterBundle.get(CHOICE)) {
            case (1) -> {
                runasStrive.getRuna().setType(CharacterType.MAGE);
                return true;
            }
            case (2) -> {
                runasStrive.getRuna().setType(CharacterType.WARRIOR);
                return true;
            }
            case (3) -> {
                runasStrive.getRuna().setType(CharacterType.PALADIN);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETER_LIST;
    }
}
