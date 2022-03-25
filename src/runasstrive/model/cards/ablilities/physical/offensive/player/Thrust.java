package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.entity.Entity;

public class Thrust extends OffensivePhysicalPlayerAbility {
    private static final String NAME = "Thrust";

    public Thrust(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
