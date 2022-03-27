package runasstrive.controller.gamestates;

import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;
import runasstrive.view.resources.Messages;

import java.util.List;

public abstract class GameState {
    protected final RunasStrive runasStrive;
    protected String response;
    protected Class<? extends GameState> nextGameState;

    protected GameState(RunasStrive runasStrive) {
        this.runasStrive = runasStrive;
    }

    public String getResponse() {
        return this.response;
    }

    public Class<? extends GameState> getNext() {
        return this.nextGameState;
    }

    public abstract String getPrompt();

    public abstract String repeatPrompt();

    public abstract boolean execute(ParameterBundle parameterBundle);

    public abstract Parameter<?> getParameter();

    protected String list(List<?> toList, int list_index_offset) {
        final StringBuilder listBuilder = new StringBuilder();
        toList.forEach(entry -> {
            final int index = toList.indexOf(entry);
            final String el = toList.get(index).toString();
            listBuilder
                    .append(String.format(Messages.LIST_ELEMENT, index + list_index_offset, el))
                    .append(System.lineSeparator());
        });
        return listBuilder.toString().trim();
    }

    /**
     * This method performs the interaction with {@link RunasStrive} using the given input
     * parameters.
     *
     * @param parameterBundle holds all required parameters for the game state
     * @return a boolean based on whether the interaction with the given input was performed
     * successfully
     */
    protected abstract boolean interact(ParameterBundle parameterBundle);

    /**
     * This method sets the next game state based on the previous interaction with {@link RunasStrive}.
     */
    protected abstract void setNextGameState();

    /**
     * This method sets the response based on the previous interaction with {@link RunasStrive}.
     */
    protected abstract void setResponse();

    /**
     * This method represents an entire game state execution from the interaction with
     * {@link RunasStrive} to setting the response accordingly.
     *
     * @param parameterBundle holds all required {@link Parameter} objects for the game state
     * @return a boolean based on whether the execution was successful or failed
     * at any point
     */
    protected boolean abstractExecute(ParameterBundle parameterBundle) {
        if (interact(parameterBundle)) {
            setNextGameState();
            setResponse();
            return true;
        }
        return false;
    }

}
