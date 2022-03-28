package runasstrive.model.cards.ablilities.physical;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.type.MonsterType;

/**
 * This class represents an abstract physical {@link Ability}
 * card and serves as a super class for all physical
 * ability cards.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class PhysicalAbility extends Ability {

    /**
     * This integer represents the ability's cost which is
     * generally 0 for physical abilities.
     */
    protected static final int COST = 0;
    private static final int ADDITIONAL_TYPE_DAMAGE = 0;
    private static final MonsterType AFFECTED_TYPE = MonsterType.NONE;

    /**
     * This method serves as a super constructor for all abilities
     * inheriting from PhysicalAbility.
     *
     * @param name the ability's name
     * @param level the ability's level
     * @param requiresTarget the need for choosing a target
     * @param requiresDieRoll the need for a die roll result
     */
    protected PhysicalAbility(String name, int level, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level, COST, requiresTarget, requiresDieRoll);
    }

    @Override
    public MonsterType getAffectedType() {
        return AFFECTED_TYPE;
    }

    @Override
    public int getAdditionalTypeDamage() {
        return ADDITIONAL_TYPE_DAMAGE;
    }
}
