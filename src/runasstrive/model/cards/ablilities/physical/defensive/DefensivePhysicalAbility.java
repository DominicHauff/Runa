package runasstrive.model.cards.ablilities.physical.defensive;

import runasstrive.model.cards.ablilities.physical.PhysicalAbility;

public abstract class DefensivePhysicalAbility extends PhysicalAbility {
    protected static final boolean REQUIRES_TARGET = false;
    protected static final boolean REQUIRES_DIE_ROLL = false;
    protected static final int PHYSICAL_DAMAGE = 0;
    protected static final int MAGIC_DAMAGE = 0;
    protected static final int REFLECT_PHYSICAL_DAMAGE = 0;
    protected static final int REFLECT_MAGIC_DAMAGE = 0;
    protected static final int MAGIC_SHIELD = 0;
    protected static final boolean BREAK_FOCUS_POINTS = false;
    protected static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    protected DefensivePhysicalAbility(String name, int level) {
        super(name, level, REQUIRES_TARGET, REQUIRES_DIE_ROLL);
    }
}
