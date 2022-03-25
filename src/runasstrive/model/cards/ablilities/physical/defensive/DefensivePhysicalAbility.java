package runasstrive.model.cards.ablilities.physical.defensive;

import runasstrive.model.cards.ablilities.physical.PhysicalAbility;

public abstract class DefensivePhysicalAbility extends PhysicalAbility {
    protected static final boolean REQUIRES_TARGET = false;
    protected static final boolean REQUIRES_DIE_ROLL = false;

    protected DefensivePhysicalAbility(String name, int level) {
        super(name, level, REQUIRES_TARGET, REQUIRES_DIE_ROLL);
    }
}
