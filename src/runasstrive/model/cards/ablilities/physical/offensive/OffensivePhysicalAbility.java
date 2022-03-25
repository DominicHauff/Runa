package runasstrive.model.cards.ablilities.physical.offensive;

import runasstrive.model.cards.ablilities.physical.PhysicalAbility;

public abstract class OffensivePhysicalAbility extends PhysicalAbility {
    protected static boolean REQUIRES_TARGET = true;
    protected static final int MAGIC_DAMAGE = 0;
    protected static final int REFLECT_PHYSICAL_DAMAGE = 0;
    protected static final int REFLECT_MAGIC_DAMAGE = 0;
    protected static final int PHYSICAL_SHIELD = 0;
    protected static final int MAGIC_SHIELD = 0;
    protected static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    protected OffensivePhysicalAbility(String name, int level, boolean requiresDieRoll) {
        super(name, level, REQUIRES_TARGET, requiresDieRoll);
    }
}
