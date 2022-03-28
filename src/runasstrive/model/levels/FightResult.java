package runasstrive.model.levels;

/**
 * An enum defining all possible results of a fight interaction between runa and monsters.
 *
 * @author ugget
 * @version 1.0
 */
public enum FightResult {

    /**
     * The game ends, runa is dead
     */
    GAME_OVER,

    /**
     * The game ends, runa wins
     */
    GAME_WON,

    /**
     * Runa has cleared a stage and can advance to the next stage
     */
    STAGE_CLEARED,

    /**
     * Runa has defeated a boss monster and therefore cleared a whole level
     */
    LEVEL_CLEARED,

    /**
     * Nothing special happened, runa need to continue fighting the monsters of the current stage
     */
    CONTINUE
}
