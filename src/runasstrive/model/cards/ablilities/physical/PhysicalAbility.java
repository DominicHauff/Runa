package runasstrive.model.cards.ablilities.physical;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.type.MonsterType;

public abstract class PhysicalAbility extends Ability {
    protected static final int COST = 0;
    private static final int ADDITIONAL_TYPE_DAMAGE = 0;
    private static final MonsterType AFFECTED_TYPE = MonsterType.NONE;

    protected PhysicalAbility(String name, int level, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level, COST, requiresTarget, requiresDieRoll);
    }

    @Override
    public MonsterType getAffectedType() {
        return AFFECTED_TYPE;
    }

    @Override
    public int getAdditionalTypeDamage() {
        return ADDITIONAL_TYPE_DAMAGE;
    }
}
