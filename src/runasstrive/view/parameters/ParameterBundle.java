package runasstrive.view.parameters;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a parameter bundle,
 * more specifically a map containing a game states required
 * parameters as keys and the corresponding parameter types
 * as values.
 *
 * @author ugget
 * @version 1.0
 */
public class ParameterBundle {
    private final Map<Parameter<?>, Object> parameters;

    /**
     * This method constructs a new ParameterBundle object.
     */
    public ParameterBundle() {
        this.parameters = new HashMap<>();
    }

    /**
     * This method sets a new key/value pair
     *
     * @param key a game state parameter
     * @param parsingResult the received value from the parameter's get method
     */
    public void put(Parameter<?> key, Object parsingResult) {
        if (key.getType() != parsingResult.getClass()) {
            System.out.printf("keyType: %s, parsingResultType: %s%n", key.getType().getSimpleName(),
                    parsingResult.getClass().getSimpleName());
            throw new IllegalStateException();
        }
        this.parameters.put(key, parsingResult);
    }

    /**
     * @param key a game state parameter
     * @param <T> the parameter's type
     * @return returns the parameter type corresponding to the given key
     */
    @SuppressWarnings("unchecked")
    public <T> T get(Parameter<T> key) {
        return (T) this.parameters.get(key);
    }
}
