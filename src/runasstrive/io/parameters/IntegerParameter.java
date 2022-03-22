package runasstrive.io.parameters;

public class IntegerParameter extends Parameter<Integer> {
    @Override
    public Integer get(String parsingResult) {
        try {
            return Integer.parseInt(parsingResult);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public Class<?> getType() {
        return Integer.class;
    }
}
