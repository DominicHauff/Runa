package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

public class Water extends OffensiveMagicAbility {
    private static final String NAME = "Water";
    private static final MonsterType AFFECTED_MONSTER_TYPE = MonsterType.LIGHTNING;

    public Water(int level, int cost) {
        super(NAME, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        //TODO: implement
    }

    @Override
    public MonsterType getAffectedType() {
        return AFFECTED_MONSTER_TYPE;
    }
}
