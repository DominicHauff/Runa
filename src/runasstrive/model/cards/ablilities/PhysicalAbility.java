package runasstrive.model.cards.ablilities;

import runasstrive.model.Level;

public abstract class PhysicalAbility extends Ability {


    protected PhysicalAbility(String name, int level, int cost, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level, cost, requiresTarget, requiresDieRoll);
    }
}
