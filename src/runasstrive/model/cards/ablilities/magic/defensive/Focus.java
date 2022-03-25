package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.entity.Entity;

public class Focus extends DefensiveMagicAbility {
    private static final String NAME = "Focus";

    public Focus(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
         //TODO: implement
    }
}
