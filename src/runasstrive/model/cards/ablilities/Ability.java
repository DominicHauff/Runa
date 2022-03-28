package runasstrive.model.cards.ablilities;

import runasstrive.view.resources.Messages;
import runasstrive.model.cards.Card;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

/**
 * This class represents an abstract ability
 * card and serves as a super class for all
 * ability cards.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class Ability extends Card {

    /**
     * This integer represents the cost of an ability
     * which only applies to magic abilities.
     */
    protected final int cost;

    /**
     * This boolean represents the need for choosing
     * a target against which the card is used.
     */
    protected final boolean requiresTarget;

    /**
     * This boolean represents the need for a die roll
     * in order do deal additional damage.
     */
    protected final boolean requiresDieRoll;

    /**
     * This integer represents the card's level.
     */
    protected int level;

    /**
     * This integer represents the amount of physical damage
     * dealt by the ability.
     */
    protected int physicalDamage;

    /**
     * This integer represents the amount of magic damage
     * dealt by the ability.
     */
    protected int magicDamage;

    /**
     * This integer represents the amount of physical
     * damage that gets reflected by the ability.
     */
    protected int reflectPhysicalDamage;

    /**
     * This integer represents the amount of magic
     * damage that gets reflected by the ability.
     */
    protected int reflectMagicDamage;

    /**
     * This integer represents the amount of taken physical
     * damage that gets reduced by the ability.
     */
    protected int physicalShield;

    /**
     * This integer represents the amount of taken magic
     * damage that gets reduced by the ability.
     */
    protected int magicShield;

    /**
     * This boolean represents whether the ability
     * has the focus effect, which in this application
     * only the focus ability has.
     */
    protected boolean willIncreaseFocusPoints;

    /**
     * This ability represents whether the ability breaks
     * the focus state of the target.
     */
    protected boolean breakFocus;

    /**
     * This method serves as a super constructor for all abilities.
     *
     * @param name the ability's name
     * @param level the ability's level
     * @param cost the ability's cost
     * @param requiresTarget the need for choosing a target
     * @param requiresDieRoll the need for a die roll
     */
    protected Ability(String name, int level, int cost, boolean requiresTarget, boolean requiresDieRoll) {
        super(name);
        this.level = level;
        this.cost = cost;
        this.requiresTarget = requiresTarget;
        this.requiresDieRoll = requiresDieRoll;
    }

    /**
     * This method applies the ability's effects on the caster
     * and target.
     *
     * @param caster the entity casting the ability
     * @param target the caster's target
     */
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

    /**
     * This method applies the ability's effects on the caster.
     *
     * @param caster the entity casting the ability
     */
    public void use(Entity<?> caster) {
        initValues(caster);
        caster.shield(physicalShield, magicShield);
        caster.reflect(reflectPhysicalDamage, reflectMagicDamage);
        caster.setFocus(willIncreaseFocusPoints);
    }

    /**
     * This method initializes all variables in order to
     * apply the ability's effect.
     *
     * @param caster the entity casting the ability
     */
    public abstract void initValues(Entity<?> caster);

    @Override
    public String toString() {
        return String.format(Messages.ABILITY_NAME, this.getName(), this.level);
    }

    /**
     * This method returns a boolean based on whether
     * a target is required or not.
     *
     * @return the need for choosing a target
     */
    public boolean targetRequired() {
        return requiresTarget;
    }

    /**
     * This method returns a boolean based on whether
     * a die roll is required or not
     *
     * @return the need for a die roll
     */
    public boolean dieRollRequired() {
        return requiresDieRoll;
    }

    /**
     * This method returns the cost of the ability.
     *
     * @return the cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * This method returns the amount of additional
     * damage dealt to the affected element type.
     *
     * @return the additional damage
     */
    public abstract int getAdditionalTypeDamage();

    /**
     * This method returns the element type to which
     * additional damage is dealt by the ability.
     *
     * @return the affected type
     */
    public abstract MonsterType getAffectedType();

    /**
     * This method returns the ability's level.
     *
     * @return the level
     */
    public int getLevel() {
        return this.level;
    }
}
