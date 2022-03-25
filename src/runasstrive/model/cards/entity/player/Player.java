package runasstrive.model.cards.entity.player;

import runasstrive.io.resources.Messages;
import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.CharacterType;

import java.util.Collection;
import java.util.Collections;

public class Player extends Entity<CharacterType> {
    private final int maxHp;
    private final int heal;
    private final int minAbilityCards ;
    private int hp = 50;
    private int maxFp;
    private Ability cardToPlay;
    private Monster target;
    private int dieRes;

    public Player(String name, int heal, int minAbilityCards, int hp, int fp, int maxFp, Collection<Ability> abilities) {
        super(name, hp, abilities);
        this.maxHp = hp;
        this.fp = fp;
        this.heal = heal;
        this.maxFp = maxFp;
        this.minAbilityCards = minAbilityCards;
    }

    @Override
    public String toString() {
        return String.format(Messages.PLAYER_STATS, this.getName(), this.getHp(), this.getFp());
    }

    public Ability chooseCard(int index) {
        this.cardToPlay = this.getAbilities().size() <= index ? null : this.getAbilities().get(index);
        return this.cardToPlay;
    }

    public Ability getCardToPlay() {
        return this.cardToPlay;
    }

    public Monster getTarget() {
        return this.target;
    }

    @Override
    public void setType(CharacterType type) {
        super.setType(type);
        this.abilities.addAll(type.getTypeAbilities());
    }

    public void setTarget(Monster target) {
        this.target = target;
    }

    public int getDieRes() {
        return dieRes;
    }

    public void setDieRes(int dieRes) {
        this.dieRes = dieRes;
    }

    @Override
    public Ability nextAbility() {
        return this.cardToPlay;
    }

    @Override
    public Class<?> getEntityType() {
        return CharacterType.class;
    }

    @Override
    public void focus(int fp) {
        if (this.maxFp < this.fp + fp) {
            this.fp = this.maxFp;
            return;
        }
        this.fp += fp;
    }

    public boolean canHeal() {
        return this.hp < maxHp  && this.abilities.size() > minAbilityCards;
    }

    public void upgradeCharacterCards() {
        this.getType().getTypeAbilities().forEach(Ability::upgrade);
    }

    public void addAbility(Ability ability) {
        this.abilities.addLast(ability);
    }

    public void heal(int choice) {
        this.abilities.remove(choice);
        this.hp = Math.min(maxHp, this.hp + heal);
    }
}
