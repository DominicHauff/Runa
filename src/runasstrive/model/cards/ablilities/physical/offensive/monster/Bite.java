package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.entity.Entity;

public class Bite extends OffensivePhysicalMonsterAbility {
    private static final String NAME = "Bite";

    protected Bite(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
