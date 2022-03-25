package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;

public class Water extends OffensiveMagicAbility {
    private static final String NAME = "Water";

    public Water(int level, int cost) {
        super(NAME, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
