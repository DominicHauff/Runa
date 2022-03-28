package runasstrive.model.cards.ablilities.magic;

import runasstrive.model.cards.ablilities.Ability;

/**
 * This class represents an abstract magic {@link Ability} card which
 * like physical abilities can either be offensive or defensive
 * and has a certain effect.
 * It serves as a super class for all magic abilities in the game.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class MagicAbility extends Ability {

    /**
     * This boolean represents the need for a die roll
     * which is generally not required for magic abilities.
     */
    protected static final boolean REQUIRES_DIE_ROLL = false;

    /**
     * This integer represents the amount of physical damage
     * dealt by the ability which is generally 0 for magic
     * abilities.
     */
    protected static final int PHYSICAL_DAMAGE = 0;

    /**
     * This integer represents the amount of physical damage
     * that gets reflected by the ability which is generally
     * 0 for magic abilities.
     */
    protected static final int REFLECT_PHYSICAL_DAMAGE = 0;

    /**
     * This integer represents the amount of inflicted physical damage that
     * gets reduced by the ability which is generally 0 for magic abilities
     */
    protected static final int PHYSICAL_SHIELD = 0;

    private static final int ADDITIONAL_TYPE_DAMAGE_FACTOR = 2;

    /**
     * This method serves as a super constructor for all objects that inherit
     * from MagicAbility
     *
     * @param name the ability's name
     * @param level the ability's level
     * @param cost the ability's cost
     * @param requiresTarget the need for choosing a target
     */
    protected MagicAbility(String name, int level, int cost, boolean requiresTarget) {
        super(name, level, cost, requiresTarget, REQUIRES_DIE_ROLL);
    }

    @Override
    public int getAdditionalTypeDamage() {
        return ADDITIONAL_TYPE_DAMAGE_FACTOR * this.level;
    }
}
