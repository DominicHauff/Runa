package controller;

import controller.gameStates.ChooseCharacterClass;
import controller.gameStates.GameState;
import controller.gameStates.InitializeLevel;
import runasstrive.model.RunasStrive;

import java.util.LinkedList;
import java.util.List;

public class GameStateSupplier {
    private final LinkedList<GameState> gameStates;

    public GameStateSupplier(RunasStrive runasStrive) {
        this.gameStates = new LinkedList<>(List.of(
                new ChooseCharacterClass(runasStrive),
                new InitializeLevel(runasStrive)
        ));
    }

    public LinkedList<GameState> getGameStates() {
        return this.gameStates;
    }
}
