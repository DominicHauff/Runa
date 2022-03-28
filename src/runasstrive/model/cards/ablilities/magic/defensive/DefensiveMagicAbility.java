package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.ablilities.magic.MagicAbility;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

/**
 * This class represents an abstract defensive {@link MagicAbility}
 * card and serves as a super class for all ability cards belonging
 * to this category.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class DefensiveMagicAbility extends MagicAbility {

    /**
     * This boolean represents the need for choosing a target,
     * which is not the case for this type of ability
     */
    protected static final boolean REQUIRES_TARGET = false;

    /**
     * This integer represents the cost of an ability which is
     * generally 0 for defensive abilities.
     */
    protected static final int COST = 0;

    /**
     * This integer represents the magic damage dealt by the ability
     * which is generally 0 for defensive abilities.
     */
    protected static final int MAGIC_DAMAGE = 0;

    /**
     * This boolean represents the breaking effect for the focus state
     * which is generally false for defensive abilities
     */
    protected static final boolean BREAK_FOCUS_POINTS = false;

    private static final MonsterType AFFECTED_TYPE = MonsterType.NONE;

    /**
     * This method serves as a super constructor for all abilities inheriting
     * from DefensiveMagicAbility.
     *
     * @param name the ability's name
     * @param level the ability's level
     */
    protected DefensiveMagicAbility(String name, int level) {
        super(name, level, COST, REQUIRES_TARGET);
    }

    @Override
    public MonsterType getAffectedType() {
        return AFFECTED_TYPE;
    }
}
