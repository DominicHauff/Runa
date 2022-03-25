package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.ablilities.magic.MagicAbility;

public abstract class OffensiveMagicAbility extends MagicAbility {
    protected static final boolean REQUIRES_TARGET = false;
    protected static final int REFLECT_MAGIC_DAMAGE = 0;
    protected static final int MAGIC_SHIELD = 0;
    protected static final boolean BREAK_FOCUS_POINTS = false;
    protected static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    protected OffensiveMagicAbility(String name, int level, int cost) {
        super(name, level, cost, REQUIRES_TARGET);
    }
}
