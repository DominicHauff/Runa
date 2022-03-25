package runasstrive.model.cards.ablilities.magic.defensive;

import runasstrive.model.cards.entity.Entity;

public class Deflect extends DefensiveMagicAbility {
    private static final String NAME = "Deflect";
    private static final int REFLECT_MAGIC_DAMAGE = 0;
    private static final int MAGIC_SHIELD_FACTOR = 11;
    private static final int MAGIC_SHIELD_ADDITIONAL_VALUE = 2;
    private static final boolean WILL_INCREASE_FOCUS_POINTS = false;

    public Deflect(int level) {
        super(NAME, level);
    }

    @Override
    public void initValues(Entity<?> caster) {
        this.physicalDamage = PHYSICAL_DAMAGE;
        this.magicDamage = MAGIC_DAMAGE;
        this.reflectPhysicalDamage = REFLECT_PHYSICAL_DAMAGE;
        this.reflectMagicDamage = REFLECT_MAGIC_DAMAGE;
        this.physicalShield = PHYSICAL_SHIELD;
        this.magicShield = MAGIC_SHIELD_FACTOR * this.level + MAGIC_SHIELD_ADDITIONAL_VALUE;
        this.willIncreaseFocusPoints = WILL_INCREASE_FOCUS_POINTS;
        this.breakFocus = BREAK_FOCUS_POINTS;
    }
}
