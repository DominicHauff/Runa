package runasstrive;

import runasstrive.builder.GameBuilder;
import runasstrive.controller.Controller;
import runasstrive.io.InputParser;
import runasstrive.model.RunasStrive;

public class Application {
    public static void main(String[] args) {
        if (args.length != 0) {
            System.err.println("error");//TODO: fix
            return;
        }
        InputParser inputParser = new InputParser();
        final RunasStrive runasStrive = new GameBuilder().buildGame();
        final Session session = new Session(inputParser);
        final Controller controller = new Controller(runasStrive, session);
        session.runGame(controller);
    }
}
