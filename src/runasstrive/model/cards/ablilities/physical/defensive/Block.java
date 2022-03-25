package runasstrive.model.cards.ablilities.physical.defensive;

import runasstrive.model.cards.entity.Entity;

public class Block extends DefensivePhysicalAbility {
    protected static final String NAME = "Block";

    protected Block(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
