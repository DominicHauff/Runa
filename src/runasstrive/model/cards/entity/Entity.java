package runasstrive.model.cards.entity;

import runasstrive.model.Level;
import runasstrive.model.cards.Card;
import runasstrive.model.cards.ablilities.Ability;

import java.util.LinkedList;
import java.util.List;

public abstract class Entity<T> extends Card {
    protected final LinkedList<Ability> abilities;
    protected T type;
    protected int hp;
    protected int fp;
    protected int physicalShield;
    protected int magicalShield;
    protected int reflectPhysicalDamage;
    protected int reflectMagicalDamage;
    protected boolean increaseFp;

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

    public void shield(int physicalShield, int magicalShield) {
        //TODO: implement
    }

    public void reflect(int reflectPhysicalDamage, int reflectMagicalDamage) {
        //TODO: implement
    }

    public void takeDamage(int physicalDamage, int magicalDamage) {
        //TODO: implement
    }

    public int getReflectedPhysicalDamage() {
        //TODO: implement
        return 0;
    }

    public int getReflectedMagicalDamage() {
        //TODO: implement
        return 0;
    }

    public void focus(boolean willIncreaseFocusPoints) {
        this.increaseFp = willIncreaseFocusPoints;
    }

    public void breakFocus(boolean breakFocus) {
        if (breakFocus) this.increaseFp = false;
    }

    public List<Ability> getAbilities() {
        return this.abilities;
    }
}
