package runasstrive.model.cards.entity;

import runasstrive.model.cards.Card;
import runasstrive.model.cards.ablilities.Ability;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an Entity in the game which are {@link runasstrive.model.cards.entity.player.Player}
 * and {@link runasstrive.model.cards.entity.monster.Monster} objects.
 *
 * @param <T> the entity's type which can be a {@link runasstrive.model.cards.entity.type.CharacterType}
 *           or {@link runasstrive.model.cards.entity.type.MonsterType}
 *
 * @author ugget
 * @version 1.0
 */
public abstract class Entity<T> extends Card {

    /**
     * the minimum amount of health points an entity can have
     */
    protected static final int MIN_HP = 0;

    /**
     * the minimum amount of focus points an entity can have
     */
    protected static int minFp = 0;

    /**
     * the abilities an entity can use
     */
    protected final LinkedList<Ability> abilities;

    /**
     * the entity's type
     */
    protected T type;

    /**
     * the entity's health points
     */
    protected int hp;

    /**
     * the entity's focus points
     */
    protected int fp;

    /**
     * the entity's physical shield, which gets set whenever the entity uses an ability
     * with a physical shielding effect
     */
    protected int physicalShield;

    /**
     * the entity's magic shield, which gets set whenever the entity uses an ability
     * with a magic shielding effect
     */
    protected int magicShield;

    /**
     * the entity's reflect damage, which gets set whenever the entity uses a physical reflecting ability
     */
    protected int reflectPhysicalDamage;

    /**
     * the entity's reflect damage, which gets set whenever the entity uses a magic reflecting ability
     */
    protected int reflectMagicDamage;

    /**
     * a boolean representing the focus state of the entity after using the focus ability
     */
    protected boolean increaseFp;

    /**
     * the amount of health points an entity has gained after healing itself,
     * in this application this only corresponds to the {@link runasstrive.model.cards.entity.player.Player}
     */
    protected int gainedHealth;

    /**
     * the amount of focus points an entity has gained after
     * using the focus ability
     */
    protected int gainedFp;
    private int takenPhysicalDamage;
    private int takenMagicDamage;

    /**
     * @param name the entity's name
     * @param hp the entity's initial amount of health points
     * @param abilities the entity's abilities
     */
    protected Entity(String name, int hp, Collection<Ability> abilities) {
        super(name);
        this.type = null;
        this.hp = hp;
        this.abilities = new LinkedList<>(abilities);
    }

    /**
     * @return returns the entity's type
     */
    public T getType() {
        return this.type;
    }

    /**
     * @param type the type of which the entity should be
     */
    public void setType(T type) {
        this.type = type;
    }

    /**
     * @return returns the entity's type class,
     * representing either a {@link runasstrive.model.cards.entity.player.Player}
     * or {@link runasstrive.model.cards.entity.monster.Monster}
     */
    public abstract Class<?> getEntityType();

    /**
     * @return returns the current amount of health points
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * @return returns the current amount of focus points
     */
    public int getFp() {
        return this.fp;
    }

    /**
     * sets shield values
     *
     * @param physicalShield the physical shield value after using an ability
     * @param magicShield the magic shield value after using an ability
     */
    public void shield(int physicalShield, int magicShield) {
        this.physicalShield = physicalShield;
        this.magicShield = magicShield;
    }

    /**
     * sets reflect damage values
     *
     * @param reflectPhysicalDamage the physical reflect damage value after using an ability
     * @param reflectMagicDamage the magic reflect damage value after using an ability
     */
    public void reflect(int reflectPhysicalDamage, int reflectMagicDamage) {
        this.reflectPhysicalDamage = reflectPhysicalDamage;
        this.reflectMagicDamage = reflectMagicDamage;
    }

    /**
     * @param physicalDamage the physical damage the entity takes
     * @param magicDamage the magic damage the entity takes
     */
    public void takeDamage(int physicalDamage, int magicDamage) {
        takeDamage(physicalDamage, magicDamage, null);
    }

    /**
     * sets the total amount of damage, either physical or magic after considering
     * shield and reflect damage values
     *
     * @param physicalDamage the amount of physical damage the entity takes
     * @param magicDamage the amount of magic damage the entity takes
     * @param ability the ability that was played against the entity
     */
    public void takeDamage(int physicalDamage, int magicDamage, Ability ability) {
        if (physicalDamage > this.physicalShield + this.reflectPhysicalDamage) {
            this.takenPhysicalDamage = physicalDamage - this.physicalShield - this.reflectPhysicalDamage;
        }
        if (magicDamage > this.magicShield + this.reflectMagicDamage) {
            this.takenMagicDamage = magicDamage - this.magicShield - this.reflectMagicDamage;
            if (ability != null && ability.getAffectedType().equals(this.getType())) {
                this.takenMagicDamage += ability.getAdditionalTypeDamage();
            }
        }
        this.hp -= this.takenPhysicalDamage;
        this.hp -= this.takenMagicDamage;
    }

    /**
     * @return returns the physical reflect damage value
     */
    public int getReflectedPhysicalDamage() {
        return this.reflectPhysicalDamage;
    }

    /**
     * @return returns the magic reflect damage value
     */
    public int getReflectMagicDamage() {
        return this.reflectMagicDamage;
    }

    /**
     * @param willIncreaseFocusPoints a boolean representing the entity's focus status after using an ability
     */
    public void setFocus(boolean willIncreaseFocusPoints) {
        this.increaseFp = willIncreaseFocusPoints;
    }

    /**
     * @param fp the amount of focus points the entity gains when using the focus ability
     */
    public void focus(int fp) {
        if (this.increaseFp) {
            this.fp += fp;
            this.gainedFp = fp;
        }
    }

    /**
     * @param breakFocus a boolean representing whether the entity's focus effect is broken
     */
    public void breakFocus(boolean breakFocus) {
        if (breakFocus) {
            this.increaseFp = false;
            this.gainedFp = minFp;
        }
    }

    /**
     * @return returns the entity's abilities
     */
    public List<Ability> getAbilities() {
        return this.abilities;
    }

    /**
     * @return returns the ability which will be attempted next by the entity,
     * in this application this only corresponds to {@link runasstrive.model.cards.entity.monster.Monster}
     * entities
     */
    public abstract Ability nextAbility();

    /**
     * @return returns whether an entity is dead or alive
     */
    public boolean isDead() {
        return this.hp <= MIN_HP;
    }

    /**
     * @param target the entity's next target when attacking
     * @return returns the next ability the entity is going to use
     */
    public Ability useAbility(Entity<?> target) {
        final Ability nextAbility = this.nextAbility();
        nextAbility.use(this, target);
        return nextAbility;
    }

    /**
     * @return returns the next ability the entity is going to use
     */
    public Ability useAbility() {
        final Ability nextAbility = this.nextAbility();
        nextAbility.use(this);
        return nextAbility;
    }

    /**
     * @return returns the amount of magic damage taken
     * during the last attack against the entity
     */
    public int getTakenMagicDamage() {
        return this.takenMagicDamage;
    }

    /**
     * @return This method returns the amount of physical damage
     * taken during the last attack against the entity
     */
    public int getTakenPhysicalDamage() {
        return this.takenPhysicalDamage;
    }

    /**
     * This method resets all shield values.
     */
    public void resetShield() {
        this.magicShield = 0;
        this.physicalShield = 0;
        this.reflectMagicDamage = 0;
        this.reflectPhysicalDamage = 0;
    }

    /**
     * This method resets the taken damage values.
     */
    public void resetTakenDamage() {
        this.takenMagicDamage = 0;
        this.takenPhysicalDamage = 0;
    }

    /**
     * @return the amount of gained health points
     */
    public int getGainedHp() {
        return gainedHealth;
    }

    /**
     * @return the amount of gained focus points
     */
    public int getGainedFp() {
        int gained = this.gainedFp;
        this.gainedFp = minFp;
        return gained;
    }

    /**
     * This method returns a boolean based on whether
     * the entity gained health points.
     *
     * @return if entity gained hp
     */
    public boolean hasGainedFp() {
        return this.gainedFp > minFp;
    }

    /**
     * This method reduces the entity's fp
     * according to the given cost factor.
     *
     * @param cost the ability cost factor
     */
    public void useFp(int cost) {
        this.fp -= cost;
    }
}
