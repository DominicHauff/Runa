package runasstrive.model.cards.ablilities.physical.offensive;

import runasstrive.model.cards.ablilities.physical.PhysicalAbility;

public abstract class OffensivePhysicalAbility extends PhysicalAbility {
    protected static boolean REQUIRES_TARGET = true;

    protected OffensivePhysicalAbility(String name, int level, boolean requiresDieRoll) {
        super(name, level, REQUIRES_TARGET, requiresDieRoll);
    }
}
