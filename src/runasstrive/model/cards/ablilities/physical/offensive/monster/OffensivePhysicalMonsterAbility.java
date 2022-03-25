package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.ablilities.physical.offensive.OffensivePhysicalAbility;

public abstract class OffensivePhysicalMonsterAbility extends OffensivePhysicalAbility {
    protected static final boolean REQUIRES_DIE_ROLL = false;

    protected OffensivePhysicalMonsterAbility(String name, int level) {
        super(name, level, REQUIRES_DIE_ROLL);
    }
}
