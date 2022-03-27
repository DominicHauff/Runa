package runasstrive;

import runasstrive.builder.RunaStriveBuilder;
import runasstrive.controller.Controller;
import runasstrive.io.InputParser;
import runasstrive.model.RunasStrive;

public class Application {
    public static void main(String[] args) {
        if (args.length != 0) {
            System.err.println("Error, args not 0");//TODO: remove magic string
            return;
        }
        InputParser inputParser = new InputParser();
        final RunasStrive runasStrive = RunaStriveBuilder.buildGame();
        final Session session = new Session(inputParser);
        final Controller controller = new Controller(runasStrive, session);
        session.runGame(controller);
    }
}
