package runasstrive.view.parameters;

/**
 * This class represents a singular choice input parameter.
 *
 * @author ugget
 * @version 1.0
 */
public class SingleChoiceParameter extends Parameter<Integer> {

    @Override
    public Integer get(String parsingResult) throws IllegalArgumentException {
        try {
            return Integer.parseInt(parsingResult);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getType() {
        return Integer.class;
    }
}
