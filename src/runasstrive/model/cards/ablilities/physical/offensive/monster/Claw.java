package runasstrive.model.cards.ablilities.physical.offensive.monster;

import runasstrive.model.cards.entity.Entity;

public class Claw extends OffensivePhysicalMonsterAbility {
    private static final String NAME = "Claw";
    private static final int PHYSICAL_DAMAGE_FACTOR = 6;
    private static final boolean BREAK_FOCUS_POINTS = true;

    public Claw(int level) {
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
