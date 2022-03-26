package runasstrive;

import runasstrive.controller.Controller;
import runasstrive.io.InputParser;
import runasstrive.io.resources.Messages;

import java.util.List;
import java.util.Scanner;

public class Session {
    private static final String QUIT_COMMAND = "quit";
    private final InputParser inputParser;
    private boolean running;

    public Session(InputParser inputParser) {
        this.inputParser = inputParser;
        this.running = true;
    }

    public void runGame(Controller controller) {
        System.out.print(Messages.OPENING);
        while (this.running) {
            this.processInput(controller);
        }
    }

    public void quit() {
        this.running = false;
    }

    private void processInput(Controller controller) {
        String prompt = controller.getPrompt();
        if (prompt != null) {
            System.out.print(prompt);
        }
        final String input = new Scanner(System.in).nextLine();
        if (input.equals(QUIT_COMMAND)) {
            this.quit();
            return;
        }
        List<String> argumentList = inputParser.getArgumentList(input);
        if (argumentList == null) return;
        String response = controller.interact(argumentList);
        if (response != null) {
            System.out.print(response);
        }
    }
}
