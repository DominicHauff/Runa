package runasstrive.model.cards.ablilities.physical.offensive;

import runasstrive.model.cards.ablilities.physical.PhysicalAbility;

/**
 * This class represents an abstract offensive {@link PhysicalAbility}
 * card and serves as a super class for all offensive physical
 * ability cards.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class OffensivePhysicalAbility extends PhysicalAbility {

    /**
     * This boolean represents the need for choosing a target
     * which is generally true for offensive magic abilities.
     */
    protected static final boolean REQUIRES_TARGET = true;

    /**
     * This integer represents the amount of magic damage dealt
     * by the ability which is generally 0 for physical abilities.
     */
    protected static final int MAGIC_DAMAGE = 0;

    /**
     * this integer represents the amount of physical damage
     * reflected by the ability which is generally 0 for offensive
     * abilities.
     */
    protected static final int REFLECT_PHYSICAL_DAMAGE = 0;

    /**
     * This integer represents the amount of magic damage
     * reflected by the ability which is generally 0
     * for physical abilities.
     */
    protected static final int REFLECT_MAGIC_DAMAGE = 0;

    /**
     * This integer represents the amount of physical damage
     * that gets reduced by the ability which is generally 0
     * for offensive abilities.
     */
    protected static final int PHYSICAL_SHIELD = 0;

    /**
     * This integer represents the amount of magic damage
     * that gets reduced by the ability which is generally 0
     * for physical abilities.
     */
    protected static final int MAGIC_SHIELD = 0;

    /**
     * This boolean represents the focus effect which physical
     * abilities generally don't have.
     */
    protected static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    /**
     * This method serves as a super constructor for all abilities inheriting
     * from OffensivePhysicalAbility.
     *
     * @param name the ability's name
     * @param level the ability's level
     * @param requiresDieRoll the need for a die roll result
     */
    protected OffensivePhysicalAbility(String name, int level, boolean requiresDieRoll) {
        super(name, level, REQUIRES_TARGET, requiresDieRoll);
    }
}
