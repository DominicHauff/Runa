package runasstrive.model;

import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.monster.levelonemonsters.SpiderKing;
import runasstrive.model.cards.entity.monster.leveltwomonsters.MegaSaurus;

/**
 * This enum defines the constants of a game level.
 * Each game level is defined by its number of stages and its Boss {@link  Monster}.
 *
 * @author ugget
 * @version 1.0
 */
public enum Level {

    /**
     * The constants for the first level of the game {@link RunasStrive}
     */
    LEVEL_ONE(1, 4, new int[]{1, 2, 2}, new SpiderKing()),

    /**
     * The constants for the second level of the game {@link RunasStrive}
     */
    LEVEL_TWO(2, 4, new int[]{1, 2, 2}, new MegaSaurus());

    /**
     * The constant for the game's maximum level
     */
    public static final Level MAX_LEVEL = LEVEL_TWO;
    private final int value;
    private final int numOfStages;
    private final int[] monstersPerStage;
    private final Monster levelBoss;

    /**
     * Enum constructor enhances this Level enum with attributes that define each game level.
     *
     * @param value an integer representing the game level
     * @param numOfStages the number of stages this game level has
     * @param monstersPerStage the number of monsters per stage
     * @param levelBoss the boss {@link Monster} of this game level
     */
    Level(int value, int numOfStages, int[] monstersPerStage, Monster levelBoss) {
        this.value = value;
        this.numOfStages = numOfStages;
        this.monstersPerStage = monstersPerStage;
        this.levelBoss = levelBoss;
    }

    /**
     * @return the integer representation of this game level
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return the number of stages of this game level
     */
    public int getNumOfStages() {
        return this.numOfStages;
    }

    /**
     * @return an array mapping each stage to its number of monsters
     */
    public int[] getMonstersPerStage() {
        return this.monstersPerStage;
    }

    /**
     * @return the boss {@link Monster} of this game level
     */
    public Monster getLevelBoss() {
        return this.levelBoss;
    }
}
