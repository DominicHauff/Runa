package runasstrive;

import runasstrive.controller.Controller;
import runasstrive.io.InputParser;

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
            List<String> argumentList = inputParser.getArgumentList(input);
            String response = controller.interact(argumentList);
            if (response != null) {
                System.out.println(response);
            }
        }
    }

    public void quit() {
        this.running = false;
    }
}
