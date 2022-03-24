package mockingobjects;

import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;

public class TestAbility extends Ability {

    protected TestAbility(String name, Level level, int cost, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level, cost, requiresTarget, requiresDieRoll);
    }

    @Override
    public void initValues(Entity<?> caster) {

    }
}
