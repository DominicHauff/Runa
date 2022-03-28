package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;

/**
 * This class represents the Thrust ability card.
 * It inflicts physical and additional damage
 * depending on the die result.
 *
 * @author ugget
 * @version 1.0
 */
public class Thrust extends OffensivePhysicalPlayerAbility {
    private static final String NAME = "Thrust";
    private static final int PHYSICAL_DAMAGE_FACTOR = 6;
    private static final int ADDITIONAL_DAMAGE_FACTOR = 4;
    private static final boolean BREAK_FOCUS_POINTS = false;

    /**
     * This method constructs a new Thrust object.
     *
     * @param level the ability's level
     */
    public Thrust(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        if (caster.getEntityType() == CharacterType.class) {
            final int dieRes = ((Player) caster).getDieRes();
            if (dieRes >= CRITICAL_DIE_RES) {
                this.physicalDamage
                        = PHYSICAL_DAMAGE_FACTOR * this.level + dieRes + ADDITIONAL_DAMAGE_FACTOR * this.level;
            } else {
                this.physicalDamage = PHYSICAL_DAMAGE_FACTOR * this.level + dieRes;
            }
        }
        this.magicDamage = MAGIC_DAMAGE;
        this.reflectPhysicalDamage = REFLECT_PHYSICAL_DAMAGE;
        this.reflectMagicDamage = REFLECT_MAGIC_DAMAGE;
        this.physicalShield = PHYSICAL_SHIELD;
        this.magicShield = MAGIC_SHIELD;
        this.willIncreaseFocusPoints = WILL_INCREASE_FOCUS_POINTS;
        this.breakFocus = BREAK_FOCUS_POINTS;
    }
}
