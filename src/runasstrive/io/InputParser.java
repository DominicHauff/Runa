package runasstrive.io;

import java.util.List;

public class InputParser {
    private static final String ARGUMENT_REGEX = "(\\d+(,\\d+)*)*";
    /*private static final String MULTIPLE_ARGUMENTS_REGEX = SINGLE_ARGUMENT_REGEX +
            "(" + DELIMITER + SINGLE_ARGUMENT_REGEX + ")*";*/
    //private static final String MULTIPLE_ARGUMENTS_REGEX = "\\d+(,\\d+)*";

    public List<String> getInput(String inputString) {
        boolean matches = inputString.matches(ARGUMENT_REGEX);
        return !matches ? null : List.of(inputString);
    }
}
