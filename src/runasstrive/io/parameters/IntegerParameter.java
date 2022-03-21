package runasstrive.io.parameters;

public class IntegerParameter extends Parameter<Integer> {
    @Override
    public Integer get(String parsingResult) {
        //TODO: try catch that bitch
        return Integer.parseInt(parsingResult);
    }

    @Override
    public Class<?> getType() {
        return Integer.class;
    }
}
