package runasstrive.controller;

import runasstrive.Session;
import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.GameStateSupplier;
import runasstrive.controller.gamestates.init.ChooseCharacterClass;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

/**
 * The Controller represents a managing object used to organize
 * the game's various states ({@link GameState}) and to process
 * the given user input. It contains the instance of the game
 * ({@link RunasStrive}) and a {@link GameStateSupplier} to receive
 * and call the {@link GameState#execute(ParameterBundle parameterBundle)}
 * method on each {@link GameState}, thus serving as a connection point
 * between the io interaction and the game itself.
 *
 * @author ugget
 * @version 1.0
 */
public class Controller {
    private static final String ARGUMENT_REGEX = "(\\d+(,\\d+)*)*";
    private final RunasStrive runasStrive;
    private final GameStateSupplier gameStateSupplier;
    private final Session session;
    private GameState currentGameState;
    private boolean lastInputFaulty;

    /**
     * Constructs a new controller.
     *
     * @param runasStrive the instance of the actual game
     * @param session the instance of the io session
     */
    public Controller(RunasStrive runasStrive, Session session) {
        this.runasStrive = runasStrive;
        this.gameStateSupplier = new GameStateSupplier(runasStrive);
        this.session = session;
        this.lastInputFaulty = false;
        this.currentGameState = this.gameStateSupplier.get(ChooseCharacterClass.class);
    }

    /**
     * @return returns an input prompt message corresponding to the current {@link GameState}
     */
    public String getPrompt() {
        return this.lastInputFaulty ? this.currentGameState.repeatPrompt() : this.currentGameState.getPrompt();
    }

    /**
     * Coordinates user input and game logic.
     *
     * @param input a list of io arguments created by the {@link runasstrive.io.InputParser}
     * @return returns a response message corresponding to the current {@link GameState} and user input
     */
    public String interact(String input) {
        if (!input.matches(ARGUMENT_REGEX)) {
            return null;
        }
        ParameterBundle bundle = new ParameterBundle();

        Parameter<?> parameter = this.currentGameState.getParameter();
        try {
            bundle.put(parameter, parameter.get(input));
        } catch (IllegalArgumentException e) {
            this.lastInputFaulty = true;
            return null;
        }

        if (this.currentGameState.execute(bundle)) {
            String response = currentGameState.getResponse();
            this.lastInputFaulty = false;
            Class<? extends GameState> next = this.currentGameState.getNext();
            if (next == null) this.session.quit();
            else this.currentGameState = this.gameStateSupplier.get(next);
            return response;
        }
        this.lastInputFaulty = true;
        return null;
    }
}
