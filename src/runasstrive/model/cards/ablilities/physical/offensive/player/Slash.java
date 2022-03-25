package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.entity.Entity;

public class Slash extends OffensivePhysicalPlayerAbility {
    private static final String NAME = "Slash";

    protected Slash(String name, int level) {
        super(name, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
