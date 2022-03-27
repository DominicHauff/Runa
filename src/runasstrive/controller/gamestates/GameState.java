package runasstrive.controller.gamestates;

import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;
import runasstrive.view.resources.Messages;

import java.util.List;
import java.util.stream.IntStream;

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

}
