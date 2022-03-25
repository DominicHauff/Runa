package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.entity.Entity;

public class Deflect extends DefensiveMagicAbility {
    private static final String NAME = "Deflect";

    protected Deflect(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
