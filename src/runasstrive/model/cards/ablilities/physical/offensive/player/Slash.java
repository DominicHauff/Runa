package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.entity.Entity;

public class Slash extends OffensivePhysicalPlayerAbility {
    private static final String NAME = "Slash";

    public Slash(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
