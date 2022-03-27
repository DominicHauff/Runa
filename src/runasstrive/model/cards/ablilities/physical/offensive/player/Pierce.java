package runasstrive.model.cards.ablilities.physical.offensive.player;

import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;

public class Pierce extends OffensivePhysicalPlayerAbility {
    private static final String NAME = "Pierce";
    private static final int PHYSICAL_DAMAGE_FACTOR = 7;
    private static final int ADDITIONAL_DAMAGE_FACTOR = 5;
    private static final boolean BREAK_FOCUS_POINTS = false;

    public Pierce(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        if (caster.getEntityType() == CharacterType.class) {
            final int dieRes = ((Player) caster).getDieRes();
            this.physicalDamage = PHYSICAL_DAMAGE_FACTOR * this.level + dieRes;
            if (dieRes >= CRITICAL_DIE_RES) {
                this.physicalDamage += ADDITIONAL_DAMAGE_FACTOR * this.level;
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
