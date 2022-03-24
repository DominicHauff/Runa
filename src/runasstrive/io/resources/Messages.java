package runasstrive.io.resources;

public final class Messages {
    public static final String ERROR = "This application can not be executed with any parameters.";

    //TODO: remove hard coded list
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
    public static final String ENTER_NUMBER_PROMPT = "Enter number [1--%d]:\n";
    public static final String ENTER_DICE_ROLL_PROMPT = "Enter dice roll [1--%d]:";
    public static final String STAGE_ENTER_MESSAGE = "Runa enters Stage %d of Level %d\n";
    public static final String SELECT_TARGET_MESSAGE = "Select Runa's target.";
    public static final String SEPARATOR = "----------------------------------------";
    public static final String ABILITY_NAME = "%s(%d)";
    public static final String LIST_ELEMENT = "%d) %s";
    public static final String PLAYER_STATS = "%s (%d/50 HP, %d/4 FP)";
    public static final String MONSTER_STATS = "%s (%d HP, %d FP): attempts %s next";
    public static final String VS = "vs.";
    public static final String SELECT_CARD_MESSAGE = "Select card to play";
    public static final String ENTITY_DIES = "%s dies";
    public static final String GAME_WON = "Runa won!";
    public static final String TAKE_MAGIC_DAMAGE = "%s takes %d mag. damage";
    public static final String TAKE_PHYSICAL_DAMAGE = "%s takes %d phy. damage";
    public static final String ENTITY_USES_ABILITY = "%s uses %s";


    private Messages() {
    }

}
