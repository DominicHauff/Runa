package runasstrive.model.cards.ablilities;

import runasstrive.view.resources.Messages;
import runasstrive.model.cards.Card;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

public abstract class Ability extends Card {
    protected final int cost;
    protected final boolean requiresTarget;
    protected final boolean requiresDieRoll;
    protected int level;
    protected int physicalDamage;
    protected int magicDamage;
    protected int reflectPhysicalDamage;
    protected int reflectMagicDamage;
    protected int physicalShield;
    protected int magicShield;
    protected boolean willIncreaseFocusPoints;
    protected boolean breakFocus;

    protected Ability(String name, int level, int cost, boolean requiresTarget, boolean requiresDieRoll) {
        super(name);
        this.level = level;
        this.cost = cost;
        this.requiresTarget = requiresTarget;
        this.requiresDieRoll = requiresDieRoll;
    }

    public void use(Entity<?> caster, Entity<?> target) {
        initValues(caster);
        caster.shield(physicalShield, magicShield);
        caster.reflect(reflectPhysicalDamage, reflectMagicDamage);
        target.takeDamage(physicalDamage, magicDamage, this);
        caster.takeDamage(Math.min(target.getReflectedPhysicalDamage(), physicalDamage),
                Math.min(target.getReflectMagicDamage(), magicDamage));
        caster.setFocus(willIncreaseFocusPoints);
        target.breakFocus(breakFocus);
        caster.useFp(this.cost);
    }

    public void use(Entity<?> caster) {
        initValues(caster);
        caster.shield(physicalShield, magicShield);
        caster.reflect(reflectPhysicalDamage, reflectMagicDamage);
        caster.setFocus(willIncreaseFocusPoints);
    }

    public abstract void initValues(Entity<?> caster);

    @Override
    public String toString() {
        return String.format(Messages.ABILITY_NAME, this.getName(), this.level);
    }

    public boolean targetRequired() {
        return requiresTarget;
    }

    public boolean dieRollRequired() {
        return requiresDieRoll;
    }

    public int getCost() {
        return this.cost;
    }

    public abstract int getAdditionalTypeDamage();

    public abstract MonsterType getAffectedType();

    public int getLevel() {
        return this.level;
    }

}
