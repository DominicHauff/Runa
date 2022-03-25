package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.ablilities.magic.MagicAbility;

public abstract class OffensiveMagicAbility extends MagicAbility {
    protected static final boolean REQUIRES_TARGET = false;

    protected OffensiveMagicAbility(String name, int level, int cost) {
        super(name, level, cost, REQUIRES_TARGET);
    }
}
