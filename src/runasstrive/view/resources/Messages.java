package runasstrive.view.resources;

public final class Messages {

    /**
     * An expressive error message that gets printed if the application gets started with any arguments.
     */
    public static final String ERROR = "Error, this application can not be executed with any parameters.";

    /**
     * The welcome message that gets printed before the game starts
     */
    public static final String WELCOME_MESSAGE = "Welcome to Runa's Strive";

    /**
     * Intro-Dialogue message for selecting a character class
     */
    public static final String SELECT_CHARACTER_MESSAGE = "Select Runa's character class";


    /**
     * A dialogue message that gets printed before the application shuffles the deck
     */
    public static final String ENTER_SEED_DIALOGUE = "To shuffle ability cards and monsters, enter two seeds";

    /**
     * Enter number prompt for the seeds the game needs to shuffle the cards accordingly
     */
    public static final String ENTER_SEEDS_PROMPT = "Enter seeds [1--2147483647] separated by comma:";

    /**
     * Enter number prompt for multiple numbers separated by comma
     */
    public static final String MULTIPLE_CARDS_PROMPT = "Enter numbers [1--%d] separated by comma:";

    /**
     * Enter number prompt for a single number
     */
    public static final String ENTER_NUMBER_PROMPT = "Enter number [1--%d]:";

    /**
     * Enter number prompt for a die result
     */
    public static final String ENTER_DICE_ROLL_PROMPT = "Enter dice roll [1--%d]:";

    /**
     * Dialogue message after runa enters a new stage
     */
    public static final String STAGE_ENTER_MESSAGE = "Runa enters Stage %d of Level %d";

    /**
     * Dialogue message for selecting a target during a fight
     */
    public static final String SELECT_TARGET_MESSAGE = "Select Runa's target.";

    /**
     * Separator string to increase readability
     */
    public static final String SEPARATOR = "----------------------------------------";

    /**
     * Template string for printing ability-cards
     */
    public static final String ABILITY_NAME = "%s(%d)";

    /**
     * Template string for printing a listable element
     */
    public static final String LIST_ELEMENT = "%d) %s";

    /**
     * Template string for printing runa
     */
    public static final String PLAYER_STATS = "%s (%d/50 HP, %d/%d FP)";

    /**
     * Template string for printing a monster with its stats
     */
    public static final String MONSTER_STATS = "%s (%d HP, %d FP): attempts %s next";

    /**
     * VS. String to print the fight situation before runa picks her card
     */
    public static final String VS = "vs.";

    /**
     * Dialogue message for selecting a card to play
     */
    public static final String SELECT_CARD_MESSAGE = "Select card to play";

    /**
     * Message that gets printed if an entity dies
     */
    public static final String ENTITY_DIES = "%s dies";

    /**
     * Message that gets printed if runa wins the game
     */
    public static final String GAME_WON = "Runa won!";

    /**
     * Template string to print that an entity has taken magic damage
     */
    public static final String TAKE_MAGIC_DAMAGE = "%s takes %d mag. damage";

    /**
     * Template string to print that an entity has taken physical damage
     */
    public static final String TAKE_PHYSICAL_DAMAGE = "%s takes %d phy. damage";

    /**
     * Template string for logging the used abilities of the entities during a fight
     */
    public static final String ENTITY_USES_ABILITY = "%s uses %s";

    /**
     * Dialogue message for choosing between rewards
     */
    public static final String CHOOSE_RUNAS_REWARD = "Choose Runa's reward";

    /**
     * Dialogue message for displaying the first reward option
     */
    public static final String CHOOSE_NEW_ABILITIES_OPTION = "1) new ability cards";

    /**
     * Dialogue message for displaying the second reward option
     */
    public static final String CHOOSE_NEW_DIE = "2) next player dice";

    /**
     * Dialogue message that gets printed if runa can choose one or more cards as loot
     */
    public static final String PICK_CARD_PROMPT = "Pick %d card(s) as loot";

    /**
     * Template string that gets printed if runa chooses to upgrade her die
     */
    public static final String UPGRADE_DIE = "Runa upgrades her die to a %s";

    /**
     * Template string for getting new cards
     */
    public static final String GET_NEW_CARD = "Runa gets %s";

    /**
     * Dialogue message that gets printed if runa can heal herself after a fight
     */
    public static final String HEALING_OPTION = "Runa (%d/50 HP) can discard ability cards for healing (or none)";

    /**
     * Template string for displaying how much health runa has gained after heal
     */
    public static final String GAIN_HEALTH = "Runa gains %d health";

    /**
     * Template string for logging that an entity gained focus points during a fight
     */
    public static final String GAIN_FOCUS_POINTS = "%s gains %d focus";

    //private constructor for utility class
    private Messages() {
    }
}
