package runasstrive.io.parameters;

public class SeedParameter extends Parameter<Integer> {
    private static final int MIN_SEED = 1;

    @Override
    public Integer get(String parsingResult) throws IllegalArgumentException {
        try {
            int seed = Integer.parseInt(parsingResult);
            if (seed < MIN_SEED) {
                throw new IllegalArgumentException();
            }
            return seed;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getType() {
        return Integer.class;
    }
}
