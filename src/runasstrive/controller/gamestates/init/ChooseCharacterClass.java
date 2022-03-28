package runasstrive.controller.gamestates.init;

import runasstrive.controller.gamestates.GameState;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.parameters.SingleChoiceParameter;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.entity.type.CharacterType;

import java.util.Arrays;

/**
 * This class represents the {@link GameState} in which
 * the player has to choose which character class he wants to
 * play the game as.
 *
 * @author ugget
 * @version 1.0
 */
public class ChooseCharacterClass extends GameState {
    private static final SingleChoiceParameter CHOICE = new SingleChoiceParameter();

    /**
     * This method constructs a new ChooseCharacterClass object.
     *
     * @param runasStrive the instance of the {@link RunasStrive} object used by all
     *                    game states
     */
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
    public Parameter<?> getParameter() {
        return CHOICE;
    }

    @Override
    protected boolean interact(ParameterBundle parameterBundle) {
        final int choice = parameterBundle.get(CHOICE);
        return this.runasStrive.chooseCharacterType(choice);
    }

    @Override
    protected void setNextGameState() {
        this.nextGameState = InitializeLevel.class;
    }

    @Override
    protected void setResponse() {
        this.response = null;
    }
}
