package runasstrive.model.cards.ablilities.magic;

import runasstrive.model.cards.ablilities.Ability;

public abstract class MagicAbility extends Ability {
    protected static final boolean REQUIRES_DIE_ROLL = false;
    protected static final int PHYSICAL_DAMAGE = 0;
    protected static final int REFLECT_PHYSICAL_DAMAGE = 0;
    protected static final int PHYSICAL_SHIELD = 0;

    protected MagicAbility(String name, int level, int cost, boolean requiresTarget) {
        super(name, level, cost, requiresTarget, REQUIRES_DIE_ROLL);
    }
}
