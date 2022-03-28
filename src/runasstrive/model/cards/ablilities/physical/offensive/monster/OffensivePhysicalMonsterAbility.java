package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.ablilities.physical.offensive.OffensivePhysicalAbility;

/**
 * This class represents an abstract offensive physical ability
 * card and serves as a super class for all offensive physical ability cards
 * used exclusively by monsters.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class OffensivePhysicalMonsterAbility extends OffensivePhysicalAbility {
    /**
     * This boolean represents the need for a die roll
     * which monster abilities generally don't need.
     */
    protected static final boolean REQUIRES_DIE_ROLL = false;

    /**
     * This method serves as a super constructor for all abilities
     * inheriting from OffensivePhysicalMonsterAbility.
     *
     * @param name the ability's name
     * @param level the ability's level
     */
    protected OffensivePhysicalMonsterAbility(String name, int level) {
        super(name, level, REQUIRES_DIE_ROLL);
    }
}
