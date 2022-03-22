package runasstrive.io.resources;

public abstract class Messages {
    public static final String ERROR = "This application can not be executed with any parameters.";

    public static final String OPENING = """
            Welcome to Runa's Strive
            Select Runa's character class
            1) Warrior
            2) Mage
            3) Paladin
            """;

    public static final String ENTER_SEEDS_PROMPT = """
            To shuffle ability cards and monsters, enter two seeds
            Enter seeds [1--2147483647] separated by comma:
            """;

    public static String getEnterNumberPrompt(int[] range) {
        final int start = range[0];
        final int end = range[1];
        return String.format("Enter number [%d--%d]:\n", start, end);
    }
}
