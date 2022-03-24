package runasstrive.io.parameters;

public class CardIndexParameter extends Parameter<Integer> {
    private static final int OFFSET = 1;

    @Override
    public Integer get(String parsingResult) throws IllegalArgumentException {
        try {
            return Integer.parseInt(parsingResult) - OFFSET;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getType() {
        return null;
    }
}
