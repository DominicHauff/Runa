package runasstrive.model.cards.ablilities.magic;

import runasstrive.model.cards.ablilities.Ability;

public abstract class MagicAbility extends Ability {
    protected static final boolean REQUIRES_DIE_ROLL = false;

    protected MagicAbility(String name, int level, int cost, boolean requiresTarget) {
        super(name, level, cost, requiresTarget, REQUIRES_DIE_ROLL);
    }
}
