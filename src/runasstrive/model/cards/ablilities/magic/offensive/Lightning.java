package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;

public class Lightning extends OffensiveMagicAbility {
    private static final String NAME = "Lightning";

    protected Lightning(String name, int level, int cost) {
        super(name, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
