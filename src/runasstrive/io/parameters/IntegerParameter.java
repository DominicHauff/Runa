package runasstrive.io.parameters;

public class IntegerParameter extends Parameter<Integer> {
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
