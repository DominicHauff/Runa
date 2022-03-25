package runasstrive.model.cards.ablilities.physical.defensive;

import runasstrive.model.cards.entity.Entity;

public class Parry extends DefensivePhysicalAbility {
    protected static final String NAME = "Parry";

    public Parry(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
