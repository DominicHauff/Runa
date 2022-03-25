package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.ablilities.physical.defensive.DefensivePhysicalAbility;

public abstract class OffensivePhysicalMonsterAbility extends DefensivePhysicalAbility {
    protected static final boolean REQUIRES_DIE_ROLL = false;

    protected OffensivePhysicalMonsterAbility(String name, int level) {
        super(name, level, REQUIRES_DIE_ROLL);
    }
}
