package runasstrive;

import runasstrive.builder.RunaStriveBuilder;
import runasstrive.controller.Controller;
import runasstrive.view.Session;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;

/**
 * This class represents the application that runs Runa's Strive.
 * It creates instances of all needed objects and starts the
 * game loop in {@link Session}.
 *
 * @author ugget
 * @version 1.0
 */
public final class Application {
    private static final int EXPECTED_ARGS_SIZE = 0;

    private Application() {
    }

    /**
     * The program's main method.
     *
     * @param args parameters of which there should not be any
     */
    public static void main(String[] args) {
        if (args.length != EXPECTED_ARGS_SIZE) {
            System.err.println(Messages.ERROR);
            return;
        }
        final RunasStrive runasStrive = RunaStriveBuilder.buildGame();
        final Session session = new Session();
        final Controller controller = new Controller(runasStrive, session);
        session.runGame(controller);
    }

}
