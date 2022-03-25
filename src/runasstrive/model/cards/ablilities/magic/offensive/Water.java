package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;

public class Water extends OffensiveMagicAbility {
    private static final String NAME = "Water";

    protected Water(String name, int level, int cost) {
        super(name, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
