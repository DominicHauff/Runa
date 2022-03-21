package runasstrive.io.parameters;

import java.util.HashMap;
import java.util.Map;

public abstract class ParameterBundle {
    private final Map<Parameter<?>, Object> parameters;

    protected ParameterBundle() {
        this.parameters = new HashMap<>();
    }

    public void put(Parameter<?> key, Object parsingResult) {
        if (key.getType() != parsingResult.getClass()) {
            throw new IllegalStateException();
        }
        this.parameters.put(key, parsingResult);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Parameter<T> key) {
        return (T) this.parameters.get(key);
    }
}
