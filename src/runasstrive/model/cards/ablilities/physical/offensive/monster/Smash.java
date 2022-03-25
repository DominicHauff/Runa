package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.entity.Entity;

public class Smash extends OffensivePhysicalMonsterAbility {
    private static final String NAME = "Smash";

    protected Smash(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
