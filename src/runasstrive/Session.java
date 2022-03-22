package runasstrive;

import controller.Controller;
import runasstrive.io.InputParser;
import runasstrive.io.resources.Messages;

import java.util.Scanner;

public class Session {
    private final InputParser inputParser;
    private final Controller controller;
    private static final String QUIT_COMMAND = "quit";
    private boolean running;

    public Session(InputParser inputParser, Controller controller) {
        this.inputParser = inputParser;
        this.controller = controller;
        this.running = true;
    }

    public void runGame() {
        System.out.print(Messages.OPENING);
        while (this.running) {
            this.processInput();
        }
    }

    public void quit() {
        this.running = false;
    }

    private void processInput() {
        String prompt = this.controller.getPrompt();
        System.out.print(prompt);
        final String input = new Scanner(System.in).nextLine();
        if (input.equals(QUIT_COMMAND)) {
            this.quit();
            return;
        }
        String response = controller.interact(inputParser.getArgumentList(input));
        if (response != null) {
            System.out.print(response);
        }
    }
}
