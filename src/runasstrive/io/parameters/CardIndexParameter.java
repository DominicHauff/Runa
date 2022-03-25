package runasstrive.io.parameters;

public class CardIndexParameter extends Parameter<Integer> {
    private static final int MIN = 0;
    private static final int OFFSET = 1;

    @Override
    public Integer get(String parsingResult) throws IllegalArgumentException {
        try {
            final int cardIndex =  Integer.parseInt(parsingResult) - OFFSET;
            if (cardIndex < MIN) {
                throw new IllegalArgumentException();
            }
            return cardIndex;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getType() {
        return null;
    }
}
