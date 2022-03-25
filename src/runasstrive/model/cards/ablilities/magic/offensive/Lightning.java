package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

public class Lightning extends OffensiveMagicAbility {
    private static final String NAME = "Lightning";
    private static final MonsterType AFFECTED_MONSTER_TYPE = MonsterType.FIRE;

    public Lightning(int level, int cost) {
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
