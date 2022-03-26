package runasstrive.io.parameters;

import java.util.HashMap;
import java.util.Map;

public class ParameterBundle {
    private final Map<Parameter<?>, Object> parameters;

    public ParameterBundle() {
        this.parameters = new HashMap<>();
    }

    public void put(Parameter<?> key, Object parsingResult) {
        if (key.getType() != parsingResult.getClass()) {
            System.out.printf("keyType: %s, parsingResultType: %s%n", key.getType().getSimpleName(), parsingResult.getClass().getSimpleName());
            throw new IllegalStateException();
        }
        this.parameters.put(key, parsingResult);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Parameter<T> key) {
        return (T) this.parameters.get(key);
    }

    public boolean isPresent(Parameter<?> key) {
        return this.parameters.containsKey(key);
    }

}
