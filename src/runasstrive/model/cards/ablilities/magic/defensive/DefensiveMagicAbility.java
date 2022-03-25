package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.ablilities.magic.MagicAbility;

public abstract class DefensiveMagicAbility extends MagicAbility {
    protected static final boolean REQUIRES_TARGET = false;
    protected static final int COST = 0;
    protected static final int MAGIC_DAMAGE = 0;
    protected static final boolean BREAK_FOCUS_POINTS = false;

    protected DefensiveMagicAbility(String name, int level) {
        super(name, level, COST, REQUIRES_TARGET);
    }
}
