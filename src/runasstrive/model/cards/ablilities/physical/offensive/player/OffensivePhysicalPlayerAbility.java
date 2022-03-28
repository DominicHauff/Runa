package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.ablilities.physical.offensive.OffensivePhysicalAbility;

/**
 * This class represents an abstract
 * offensive physical player ability
 * card and serves as a super class
 * for all offensive physical ability cards
 * used exclusively by the player.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class OffensivePhysicalPlayerAbility extends OffensivePhysicalAbility {

    /**
     * This boolean represents the need for a die roll
     * which offensive physical player abilities generally need.
     */
    protected static final boolean REQUIRES_DIE_ROLL = true;

    /**
     * This integer represents the minimum die roll result
     * the ability needs to deal additional damage.
     */
    protected static final int CRITICAL_DIE_RES = 6;

    /**
     * This method serves as a super constructor for all abilities
     * inheriting from OffensivePhysicalPlayerAbility.
     *
     * @param name the ability's name
     * @param level the ability's level
     */
    protected OffensivePhysicalPlayerAbility(String name, int level) {
        super(name, level, REQUIRES_DIE_ROLL);
    }
}
