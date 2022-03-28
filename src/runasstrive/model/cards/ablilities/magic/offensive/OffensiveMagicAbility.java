package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.ablilities.magic.MagicAbility;

/**
 * This class represents an abstract offensive {@link MagicAbility}
 * card and serves as a super class for all ability cards belonging
 * to this category.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class OffensiveMagicAbility extends MagicAbility {
    /**
     * This boolean represents the need for choosing a target,
     * which is true for this type of ability
     */
    protected static final boolean REQUIRES_TARGET = true;

    /**
     * This integer represents the amount of damage that gets
     * reflected by the ability which is generally 0 for
     * offensive abilities.
     */
    protected static final int REFLECT_MAGIC_DAMAGE = 0;

    /**
     * This integer represents the amount of magic damage reduced
     * by the ability which is generally 0 for offensive abilities
     */
    protected static final int MAGIC_SHIELD = 0;

    /**
     * This boolean represents the breaking effect for the focus state
     * which is generally false for offensive magic abilities
     */
    protected static final boolean BREAK_FOCUS_POINTS = false;

    /**
     * This boolean represents the focus effect which offensive
     * abilities generally don't have.
     */
    protected static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    /**
     * This method serves as a super constructor for all abilities inheriting
     * from OffensiveMagicAbility.
     *
     * @param name the ability's name
     * @param level the ability's level
     * @param cost the ability's cost
     */
    protected OffensiveMagicAbility(String name, int level, int cost) {
        super(name, level, cost, REQUIRES_TARGET);
    }
}
