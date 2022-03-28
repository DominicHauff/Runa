package runasstrive.model.cards.ablilities.physical.defensive;

import runasstrive.model.cards.entity.Entity;

/**
 * This class represents the Block ability.
 * It reduces the physical damage inflicted upon
 * the caster.
 *
 * @author ugget
 * @version 1.0
 */
public class Block extends DefensivePhysicalAbility {
    private static final String NAME = "Block";
    private static final int PHYSICAL_SHIELD_FACTOR = 7;

    /**
     * This method constructs a new Block object.
     *
     * @param level the ability's level
     */
    public Block(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        this.physicalDamage = PHYSICAL_DAMAGE;
        this.magicDamage = MAGIC_DAMAGE;
        this.reflectPhysicalDamage = REFLECT_PHYSICAL_DAMAGE;
        this.reflectMagicDamage = REFLECT_MAGIC_DAMAGE;
        this.physicalShield = PHYSICAL_SHIELD_FACTOR * this.level;
        this.magicShield = MAGIC_SHIELD;
        this.willIncreaseFocusPoints = WILL_INCREASE_FOCUS_POINTS;
        this.breakFocus = BREAK_FOCUS_POINTS;
    }
}
