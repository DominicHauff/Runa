package runasstrive.view;

import runasstrive.controller.Controller;

import java.util.Scanner;

/**
 * This class represents a single io session, it both takes input
 * and prints the game's output
 * received from the {@link Controller} to the terminal
 * via {@link System#out}. Furthermore, it contains the
 * main game loop which runs as long as the game is not terminated
 * either by command or a game ending event.
 * @author ugget
 * @version 1.0
 */
public class Session {
    private static final String QUIT_COMMAND = "quit";
    private boolean running;

    /**
     * This method constructs a new Session object.
     */
    public Session() {
        this.running = true;
    }

    /**
     * Runs the game by feeding input to the {@link Controller} and printing the received output.
     *
     * @param controller the game's controller, used to manage the game flow
     */
    public void runGame(Controller controller) {
        while (this.running) {
            String prompt = controller.getPrompt();
            if (prompt != null) {
                System.out.println(prompt);
            }
            final String input = new Scanner(System.in).nextLine();
            if (input.equals(QUIT_COMMAND)) {
                this.quit();
                return;
            }
            String response = controller.interact(input);
            if (response != null) {
                System.out.println(response);
            }
        }
    }

    /**
     * terminates the game loop by setting the running variable to {@code false}
     */
    public void quit() {
        this.running = false;
    }
}
