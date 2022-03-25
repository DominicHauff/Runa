package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;

public class Ice extends OffensiveMagicAbility {
    private static final String NAME = "Ice";

    protected Ice(int level, int cost) {
        super(NAME, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
