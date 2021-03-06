package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;

/**
 * This class represents the Swing ability card.
 * It inflicts physical and breaks its target's
 * focus state.
 *
 * @author ugget
 * @version 1.0
 */
public class Swing extends OffensivePhysicalPlayerAbility {
    private static final String NAME = "Swing";
    private static final int PHYSICAL_DAMAGE_FACTOR = 5;
    private static final boolean BREAK_FOCUS_POINTS = true;

    /**
     * This method constructs a new Swing object.
     *
     * @param level the ability's level
     */
    public Swing(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        if (caster.getEntityType() == CharacterType.class) {
            final int dieRes = ((Player) caster).getDieRes();
            this.physicalDamage = PHYSICAL_DAMAGE_FACTOR * this.level + dieRes;
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
