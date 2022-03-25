package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;

public class Fire extends OffensiveMagicAbility {
    private static final String NAME = "Fire";

    public Fire(int level, int cost) {
        super(NAME, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {

    }
}
