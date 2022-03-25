package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;

public class Lightning extends OffensiveMagicAbility {
    private static final String NAME = "Lightning";

    public Lightning(int level, int cost) {
        super(NAME, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }
}
