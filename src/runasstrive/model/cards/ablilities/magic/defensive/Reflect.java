package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.entity.Entity;

public class Reflect extends DefensiveMagicAbility {
    private static final String NAME = "Reflect";

    Reflect(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
