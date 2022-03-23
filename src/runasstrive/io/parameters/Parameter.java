package runasstrive.io.parameters;

public abstract class Parameter<T> {
    public abstract T get(String parsingResult) throws IllegalArgumentException;
    public abstract Class<?> getType();
}
