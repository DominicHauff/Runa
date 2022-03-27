package runasstrive.view.parameters;

public class SingleChoiceParameter extends Parameter<Integer> {

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
