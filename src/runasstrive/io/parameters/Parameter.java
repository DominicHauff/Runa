package runasstrive.io.parameters;

public abstract class Parameter<T> {
    public abstract T get(String parsingResult);
    public abstract Class<?> getType();
}
