package runasstrive.model.cards.entity;

import runasstrive.model.Level;
import runasstrive.model.cards.Card;
import runasstrive.model.cards.ablilities.Ability;

import java.util.LinkedList;
import java.util.List;

public abstract class Entity<T> extends Card {
    private static final int MIN_HP = 0;
    protected final LinkedList<Ability> abilities;
    protected T type;
    protected int hp;
    protected int fp;
    protected int physicalShield;
    protected int magicShield;
    protected int reflectPhysicalDamage;
    protected int reflectMagicDamage;
    protected boolean increaseFp;
    private int takenPhysicalDamage;
    private int takenMagicDamage;

    protected Entity(Level level, String name, int hp, List<Ability> abilities) {
        super(name, level);
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
        if (physicalDamage > this.physicalShield + this.reflectPhysicalDamage) {
            this.takenPhysicalDamage = physicalDamage - this.physicalShield - this.reflectPhysicalDamage;
            this.hp -= this.takenPhysicalDamage;
        }
        if (magicDamage > this.magicShield + this.reflectMagicDamage) {
            this.takenMagicDamage = magicDamage - this.magicShield - this.reflectMagicDamage;
            this.hp -= this.takenMagicDamage;
        }
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
        this.fp += fp;
    }

    public void breakFocus(boolean breakFocus) {
        if (breakFocus) this.increaseFp = false;
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
}
