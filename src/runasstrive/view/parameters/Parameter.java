package runasstrive.view.parameters;

/**
 * This class represents an input parameter used to differentiate input formats,
 * in this application mainly single or multiple {@code Integer} parameters.
 *
 * @param <T> either a {@link SingleChoiceParameter} or a {@link MultipleChoiceParameter},
 *           depending on the current game state
 * @author ugget
 * @version 1.0
 */
public abstract class Parameter<T> {

    /**
     * @param parsingResult a single element of the argument list held by
     * game state objects
     * @return returns a Parameter object of needed type corresponding to the needed input format
     * @throws IllegalArgumentException gets silently thrown in case of faulty input
     */
    public abstract T get(String parsingResult) throws IllegalArgumentException;

    /**
     * @return returns the parameter's Type's (T) class
     */
    public abstract Class<?> getType();
}
