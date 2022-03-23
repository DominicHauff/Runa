package runasstrive.model.cards.ablilities;

import runasstrive.io.resources.Messages;
import runasstrive.model.Level;
import runasstrive.model.cards.Card;
import runasstrive.model.cards.entity.Entity;

public abstract class Ability extends Card {
    protected final int cost;
    protected final boolean requiresTarget;
    protected final boolean requiresDieRoll;
    protected int physicalDamage;
    protected int magicalDamage;
    protected int reflectPhysicalDamage;
    protected int reflectMagicalDamage;
    protected int physicalShield;
    protected int magicalShield;
    protected boolean willIncreaseFocusPoints;
    protected boolean breakFocus;



    protected Ability(String name, Level level, int cost, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level);
        this.cost = cost;
        this.requiresTarget = requiresTarget;
        this.requiresDieRoll = requiresDieRoll;
    }

    public void use(Entity<?> caster, Entity<?> target) {
        initValues();
        caster.shield(physicalShield, magicalShield);
        caster.reflect(reflectPhysicalDamage, reflectMagicalDamage);
        target.takeDamage(physicalDamage, magicalDamage);
        caster.takeDamage(target.getReflectedPhysicalDamage(), target.getReflectedMagicalDamage());
        caster.focus(willIncreaseFocusPoints);
        target.breakFocus(breakFocus);
    }

    public void use(Entity<?> caster) {
        initValues();
        caster.shield(physicalShield, magicalShield);
        caster.reflect(reflectPhysicalDamage, reflectMagicalDamage);
        caster.focus(willIncreaseFocusPoints);
    }

    public abstract void initValues();

    @Override
    public String toString() {
        return String.format(Messages.ABILITY_NAME, this.getName(), this.level.getValue());
    }

    public boolean targetRequired() {
        return requiresTarget;
    }

    public boolean dieRollRequired() {
        return requiresDieRoll;
    }
}
