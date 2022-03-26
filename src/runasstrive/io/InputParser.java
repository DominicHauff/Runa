package runasstrive.io;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    private static final String DELIMITER = " ";
    private static final String SINGLE_ARGUMENT_REGEX = "(\\d+)";
    /**private static final String MULTIPLE_ARGUMENTS_REGEX = SINGLE_ARGUMENT_REGEX +
            "(" + DELIMITER + SINGLE_ARGUMENT_REGEX + ")*";*/
    private static final String MULTIPLE_ARGUMENTS_REGEX = "\\d+(,\\d+)*";

    public List<String> getArgumentList(String inputString) {
        //boolean matches = inputString.matches(MULTIPLE_ARGUMENTS_REGEX);
        return inputString.matches(MULTIPLE_ARGUMENTS_REGEX) ? Arrays.asList(inputString.split(DELIMITER)) : null;
    }
}
