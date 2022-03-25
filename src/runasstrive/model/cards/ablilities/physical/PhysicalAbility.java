package runasstrive.model.cards.ablilities.physical;

import runasstrive.model.cards.ablilities.Ability;

public abstract class PhysicalAbility extends Ability {
    protected static final int COST = 0;

    protected PhysicalAbility(String name, int level, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level, COST, requiresTarget, requiresDieRoll);
    }
}
