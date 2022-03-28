package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.entity.Entity;

/**
 * This class represents the Reflect ability card.
 * It reflects a certain amount of magic damage
 * inflicted on the caster.
 *
 * @author ugget
 * @version 1.0
 */
public class Reflect extends DefensiveMagicAbility {
    private static final String NAME = "Reflect";
    private static final int REFLECT_MAGIC_DAMAGE_FACTOR = 10;
    private static final int MAGIC_SHIELD = 0;
    private static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    /**
     * This method constructs a new Reflect object.
     *
     * @param level the ability's level
     */
    public Reflect(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        this.physicalDamage = PHYSICAL_DAMAGE;
        this.magicDamage = MAGIC_DAMAGE;
        this.reflectPhysicalDamage = REFLECT_PHYSICAL_DAMAGE;
        this.reflectMagicDamage = REFLECT_MAGIC_DAMAGE_FACTOR * this.level;
        this.physicalShield = PHYSICAL_SHIELD;
        this.magicShield = MAGIC_SHIELD;
        this.willIncreaseFocusPoints = WILL_INCREASE_FOCUS_POINTS;
        this.breakFocus = BREAK_FOCUS_POINTS;
    }
}
