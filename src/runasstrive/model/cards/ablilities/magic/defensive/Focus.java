package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.entity.Entity;

/**
 * This class represents the Focus ability card.
 * It increases the casters number of focus points.
 *
 * @author ugget
 * @version 1.0
 */
public class Focus extends DefensiveMagicAbility {
    private static final String NAME = "Focus";
    private static final int REFLECT_MAGIC_DAMAGE = 0;
    private static final int MAGIC_SHIELD = 0;
    private static final boolean WILL_INCREASE_FOCUS_POINTS = true;

    /**
     * This method constructs a new Focus object
     *
     * @param level the ability's level
     */
    public Focus(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        this.physicalDamage = PHYSICAL_DAMAGE;
        this.magicDamage = MAGIC_DAMAGE;
        this.reflectPhysicalDamage = REFLECT_PHYSICAL_DAMAGE;
        this.reflectMagicDamage = REFLECT_MAGIC_DAMAGE;
        this.physicalShield = PHYSICAL_SHIELD;
        this.magicShield = MAGIC_SHIELD;
        this.willIncreaseFocusPoints = WILL_INCREASE_FOCUS_POINTS;
        this.breakFocus = BREAK_FOCUS_POINTS;
    }
}
