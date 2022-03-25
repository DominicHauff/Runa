package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.entity.Entity;

public class Claw extends OffensivePhysicalMonsterAbility {
    private static final String NAME = "Claw";

    public Claw(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
