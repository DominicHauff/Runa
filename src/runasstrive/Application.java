package runasstrive;

import runasstrive.builder.RunaStriveBuilder;
import runasstrive.controller.Controller;
import runasstrive.io.InputParser;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

/**
 * This class represents the application that runs Runa's Strive.
 * It creates instances of all needed objects and starts the
 * game loop in {@link Session}.
 *
 * @author ugget
 * @version 1.0
 */
public class Application {

    /**
     * The program's main method.
     *
     * @param args parameters of which there should not be any, hence the error message print
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            System.err.println(Messages.ERROR);
            return;
        }
        InputParser inputParser = new InputParser();
        final RunasStrive runasStrive = RunaStriveBuilder.buildGame();
        final Session session = new Session(inputParser);
        final Controller controller = new Controller(runasStrive, session);
        session.runGame(controller);
    }
}
