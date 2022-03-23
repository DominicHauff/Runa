package runasstrive;

import runasstrive.builder.GameBuilder;
import runasstrive.controller.Controller;
import runasstrive.controller.GameStateSupplier;
import runasstrive.io.InputParser;
import runasstrive.model.RunasStrive;

public class Application {
    public static void main(String[] args) {
        if (args.length == 0) {
            RunasStrive runasStrive = new GameBuilder().buildGame();
            Controller controller = new Controller(runasStrive, new GameStateSupplier(runasStrive));
            InputParser inputParser = new InputParser();
            new Session(inputParser, controller).runGame();
        }
    }
}
