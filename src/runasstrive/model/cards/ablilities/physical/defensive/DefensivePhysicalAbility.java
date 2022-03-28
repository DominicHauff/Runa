package runasstrive.model.cards.ablilities.physical.defensive;

import runasstrive.model.cards.ablilities.physical.PhysicalAbility;

/**
 * This class represents an abstract defensive {@link PhysicalAbility}
 * card and serves as a super class for all ability cards belonging
 * to this category.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class DefensivePhysicalAbility extends PhysicalAbility {

    /**
     * This boolean represents the need for choosing a target
     * which is generally not required for defensive abilities
     */
    protected static final boolean REQUIRES_TARGET = false;

    /**
     * This boolean represents the need for a die roll
     * which is generally not required for defensive abilities
     */
    protected static final boolean REQUIRES_DIE_ROLL = false;

    /**
     * This integer represents the amount of physical damage
     * dealt by the ability which is generally 0 for
     * defensive abilities.
     */
    protected static final int PHYSICAL_DAMAGE = 0;

    /**
     * This integer represents the amount of magic damage
     * dealt by the ability which is generally 0 for
     * defensive abilities
     */
    protected static final int MAGIC_DAMAGE = 0;

    /**
     * This integer represents the amount of physical damage
     * that gets reflected by the ability which is generally 0
     * for defensive physical abilities.
     */
    protected static final int REFLECT_PHYSICAL_DAMAGE = 0;

    /**
     * This integer represents the amount of magic damage
     * that gets reflected by the ability which is generally 0
     * for physical abilities.
     */
    protected static final int REFLECT_MAGIC_DAMAGE = 0;

    /**
     * This integer represents the amount of reduced inflicted
     * magic damage on the caster which is generally 0 for physical abilities
     */
    protected static final int MAGIC_SHIELD = 0;

    /**
     * This boolean represents the breaking effect for the focus state
     * which is generally false for defensive abilities.
     */
    protected static final boolean BREAK_FOCUS_POINTS = false;

    /**
     * This boolean represents the focus effect which physical
     * abilities generally don't have.
     */
    protected static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    /**
     * This method serves as a super constructor for all abilities
     * inheriting from DefensivePhysicalAbility.
     *
     * @param name the ability's name
     * @param level the ability's level
     */
    protected DefensivePhysicalAbility(String name, int level) {
        super(name, level, REQUIRES_TARGET, REQUIRES_DIE_ROLL);
    }
}
