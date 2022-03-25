package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.ablilities.physical.offensive.OffensivePhysicalAbility;

public abstract class OffensivePhysicalPlayerAbility extends OffensivePhysicalAbility {
    protected static final boolean REQUIRES_DIE_ROLL = true;

    protected OffensivePhysicalPlayerAbility(String name, int level) {
        super(name, level, REQUIRES_DIE_ROLL);
    }
}
