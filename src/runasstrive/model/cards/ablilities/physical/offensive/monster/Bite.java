package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.entity.Entity;

/**
 * This class represents the Bite ability card.
 * It inflicts physical damage.
 *
 * @author ugget
 * @version 1.0
 */
public class Bite extends OffensivePhysicalMonsterAbility {
    private static final String NAME = "Bite";
    private static final int PHYSICAL_DAMAGE_FACTOR = 10;
    private static final boolean BREAK_FOCUS_POINTS = false;

    /**
     * This method constructs a new Bite object.
     *
     * @param level the ability's level
     */
    public Bite(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        this.physicalDamage = PHYSICAL_DAMAGE_FACTOR * this.level;
        this.magicDamage = MAGIC_DAMAGE;
        this.reflectPhysicalDamage = REFLECT_PHYSICAL_DAMAGE;
        this.reflectMagicDamage = REFLECT_MAGIC_DAMAGE;
        this.physicalShield = PHYSICAL_SHIELD;
        this.magicShield = MAGIC_SHIELD;
        this.willIncreaseFocusPoints = WILL_INCREASE_FOCUS_POINTS;
        this.breakFocus = BREAK_FOCUS_POINTS;
    }
}
