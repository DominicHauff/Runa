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
    private String output;

    public Session(InputParser inputParser) {
        this.inputParser = inputParser;
        this.running = true;
    }

    public void runGame(Controller controller) {
        this.output = Messages.OPENING;
        while (this.running) {
            this.processInput(controller);
        }
        System.out.print(output);
    }

    public void quit() {
        this.running = false;
    }

    private void processInput(Controller controller) {
        String prompt = controller.getPrompt();
        if (prompt != null) {
            this.output += prompt;
        }
        System.out.print(output);
        this.output = "";
        final String input = new Scanner(System.in).nextLine();
        if (input.equals(QUIT_COMMAND)) {
            this.quit();
            return;
        }
        List<String> argumentList = inputParser.getArgumentList(input);
        String response = controller.interact(argumentList);
        if (response != null) {
            this.output += response;
        }
    }
}
