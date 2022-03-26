package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.cards.entity.type.MonsterType;

public class Water extends OffensiveMagicAbility {
    private static final String NAME = "Water";
    private static final int MAGIC_DAMAGE_FACTOR_PLAYER = 2;
    private static final int ADDITIONAL_DAMAGE_VALUE_PLAYER = 4;
    private static final int MAGIC_DAMAGE_FACTOR_MONSTER = 8;
    private static final int ADDITIONAL_DAMAGE_VALUE_MONSTER = 2;
    private static final MonsterType AFFECTED_MONSTER_TYPE = MonsterType.LIGHTNING;

    public Water(int level, int cost) {
        super(NAME, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        this.physicalDamage = PHYSICAL_DAMAGE;

        if (caster.getEntityType() == CharacterType.class) {
            this.magicDamage =
                    (MAGIC_DAMAGE_FACTOR_PLAYER * this.level + ADDITIONAL_DAMAGE_VALUE_PLAYER) * caster.getFp();
        } else {
            this.magicDamage = MAGIC_DAMAGE_FACTOR_MONSTER * this.level + ADDITIONAL_DAMAGE_VALUE_MONSTER;
        }

        this.reflectPhysicalDamage = REFLECT_PHYSICAL_DAMAGE;
        this.reflectMagicDamage = REFLECT_MAGIC_DAMAGE;
        this.physicalShield = PHYSICAL_SHIELD;
        this.magicShield = MAGIC_SHIELD;
        this.willIncreaseFocusPoints = WILL_INCREASE_FOCUS_POINTS;
        this.breakFocus = BREAK_FOCUS_POINTS;
    }

    @Override
    public MonsterType getAffectedType() {
        return AFFECTED_MONSTER_TYPE;
    }
}