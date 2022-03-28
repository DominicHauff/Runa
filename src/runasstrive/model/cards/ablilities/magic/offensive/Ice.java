package runasstrive.model.cards.ablilities.magic.offensive;

import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.cards.entity.type.MonsterType;

/**
 * This class represents the Ice ability card.
 * It inflicts magic damage on a chosen target and
 * deals additional damage to Water type monsters.
 *
 * @author ugget
 * @version 1.0
 */
public class Ice extends OffensiveMagicAbility {
    private static final String NAME = "Ice";
    private static final int MAGIC_DAMAGE_FACTOR_PLAYER = 2;
    private static final int ADDITIONAL_DAMAGE_VALUE_PLAYER = 4;
    private static final int ADDITIONAL_DAMAGE_VALUE_CONSTANT_PLAYER = 2;
    private static final int MAGIC_DAMAGE_FACTOR_MONSTER = 10;
    private static final int ADDITIONAL_DAMAGE_VALUE_MONSTER = 2;
    private static final MonsterType AFFECTED_MONSTER_TYPE = MonsterType.WATER;

    /**
     * This method constructs a new Ice object
     *
     * @param level the ability's level
     * @param cost the ability's cost
     */
    public Ice(int level, int cost) {
        super(NAME, level, cost);
    }

    @Override
    public void initValues(Entity<?> caster) {
        this.physicalDamage = PHYSICAL_DAMAGE;

        if (caster.getEntityType() == CharacterType.class) {
            this.magicDamage
                    = (MAGIC_DAMAGE_FACTOR_PLAYER * this.level + ADDITIONAL_DAMAGE_VALUE_PLAYER) * caster.getFp()
                            + ADDITIONAL_DAMAGE_VALUE_CONSTANT_PLAYER;
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
