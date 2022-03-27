package runasstrive.controller.gamestates.init;

import runasstrive.controller.gamestates.GameState;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.parameters.SingleChoiceParameter;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.entity.type.CharacterType;

import java.util.Arrays;
import java.util.List;

public class ChooseCharacterClass extends GameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICE);

    public ChooseCharacterClass(RunasStrive runasStrive) {
        super(runasStrive);
        this.response = null;
    }

    @Override
    public String getPrompt() {
        final StringBuilder openingMessage = new StringBuilder();
        openingMessage
                .append(Messages.WELCOME_MESSAGE).append(System.lineSeparator())
                .append(Messages.SELECT_CHARACTER_MESSAGE).append(System.lineSeparator());

        Arrays.stream(CharacterType.values()).forEach(characterType -> openingMessage
                .append(String.format(Messages.LIST_ELEMENT, characterType.getValue(), characterType.getName()))
                .append(System.lineSeparator()));

        return openingMessage + repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return String.format(Messages.ENTER_NUMBER_PROMPT, CharacterType.values().length);
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE);

        if (this.runasStrive.chooseCharacterType(choice)) {
            this.response = null;
            this.nextGameState = InitializeLevel.class;
            return true;
        }

        return false;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
