package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.entity.Entity;

public class Swing extends OffensivePhysicalPlayerAbility {
    private static final String NAME = "Swing";

    protected Swing(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
