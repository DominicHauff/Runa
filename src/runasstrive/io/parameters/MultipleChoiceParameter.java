package runasstrive.io.parameters;

import java.util.*;
import java.util.stream.Collectors;

public class MultipleChoiceParameter extends Parameter<ArrayList<Integer>> {
    private static final String CHOICE_SEPARATOR = ",";
    private static final String EMPTY_INPUT = "";
    private static final int CARD_INDEX_OFFSET = 1;

    @Override
    public ArrayList<Integer> get(String parsingResult) throws IllegalArgumentException {
        try {
            if (parsingResult.equals(EMPTY_INPUT)) return new ArrayList<>();
            ArrayList<Integer> multipleCardIndices = Arrays.stream(parsingResult.split(CHOICE_SEPARATOR))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (multipleCardIndices.stream().anyMatch(integer -> integer < CARD_INDEX_OFFSET)) {
                throw new IllegalArgumentException();
            }
            return multipleCardIndices;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getType() {
        return ArrayList.class;
    }
}
