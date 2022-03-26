package runasstrive.model.cards.entity;

import runasstrive.model.cards.Card;
import runasstrive.model.cards.ablilities.Ability;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class Entity<T> extends Card {
    protected static final int MIN_HP = 0;
    protected static int MIN_FP = 0;
    protected final LinkedList<Ability> abilities;
    protected T type;
    protected int hp;
    protected int fp;
    protected int physicalShield;
    protected int magicShield;
    protected int reflectPhysicalDamage;
    protected int reflectMagicDamage;
    protected boolean increaseFp;
    protected int gainedHealth;
    protected int gainedFp;
    private int takenPhysicalDamage;
    private int takenMagicDamage;

    protected Entity(String name, int hp, Collection<Ability> abilities) {
        super(name);
        this.type = null;
        this.hp = hp;
        this.abilities = new LinkedList<>(abilities);
    }

    public T getType() {
        return this.type;
    }

    public void setType(T type) {
        this.type = type;
    }

    public abstract Class<?> getEntityType();

    public int getHp() {
        return this.hp;
    }

    public int getFp() {
        return this.fp;
    }

    public void shield(int physicalShield, int magicShield) {
        this.physicalShield = physicalShield;
        this.magicShield = magicShield;
    }

    public void reflect(int reflectPhysicalDamage, int reflectMagicDamage) {
        this.reflectPhysicalDamage = reflectPhysicalDamage;
        this.reflectMagicDamage = reflectMagicDamage;
    }

    public void takeDamage(int physicalDamage, int magicDamage) {
        takeDamage(physicalDamage, magicDamage, null);
    }

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

    public int getReflectedPhysicalDamage() {
        return this.reflectPhysicalDamage;
    }

    public int getReflectMagicDamage() {
        return this.reflectMagicDamage;
    }

    public void setFocus(boolean willIncreaseFocusPoints) {
        this.increaseFp = willIncreaseFocusPoints;
    }

    public void focus(int fp) {
        if (this.increaseFp) {
            this.fp += fp;
            this.gainedFp = fp;
        }
    }

    public void breakFocus(boolean breakFocus) {
        if (breakFocus) {
            this.increaseFp = false;
            this.gainedFp = MIN_FP;
        }
    }

    public List<Ability> getAbilities() {
        return this.abilities;
    }

    public abstract Ability nextAbility();

    public boolean isDead() {
        return this.hp <= MIN_HP;
    }

    public Ability useAbility(Entity<?> target) {
        final Ability nextAbility = this.nextAbility();
        nextAbility.use(this, target);
        return nextAbility;
    }

    public Ability useAbility() {
        final Ability nextAbility = this.nextAbility();
        nextAbility.use(this);
        return nextAbility;
    }

    public int getTakenMagicDamage() {
        return this.takenMagicDamage;
    }

    public int getTakenPhysicalDamage() {
        return this.takenPhysicalDamage;
    }

    public void resetShield() {
        this.magicShield = 0;
        this.physicalShield = 0;
        this.reflectMagicDamage = 0;
        this.reflectPhysicalDamage = 0;
    }

    public void resetTakenDamage() {
        this.takenMagicDamage = 0;
        this.takenPhysicalDamage = 0;
    }

    public int getGainedHp() {
        return gainedHealth;
    }

    public int getGainedFp() {
        int gained = this.gainedFp;
        this.gainedFp = MIN_FP;
        return gained;
    }

    public boolean hasGainedFp() {
        return this.gainedFp > MIN_FP;
    }


    public void useFp(int cost) {
        this.fp -= cost;
    }
}
