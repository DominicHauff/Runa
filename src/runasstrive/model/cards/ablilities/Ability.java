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
    protected int magicDamage;
    protected int reflectPhysicalDamage;
    protected int reflectMagicDamage;
    protected int physicalShield;
    protected int magicShield;
    protected boolean willIncreaseFocusPoints;
    protected boolean breakFocus;


    protected Ability(String name, Level level, int cost, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level);
        this.cost = cost;
        this.requiresTarget = requiresTarget;
        this.requiresDieRoll = requiresDieRoll;
    }

    public void use(Entity<?> caster, Entity<?> target) {
        initValues(caster);
        caster.shield(physicalShield, magicShield);
        caster.reflect(reflectPhysicalDamage, reflectMagicDamage);
        target.takeDamage(physicalDamage, magicDamage);
        caster.takeDamage(target.getReflectedPhysicalDamage(), target.getReflectedMagicDamage());
        caster.setFocus(willIncreaseFocusPoints);
        target.breakFocus(breakFocus);
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
        return String.format(Messages.ABILITY_NAME, this.getName(), this.level.getValue());
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
}
