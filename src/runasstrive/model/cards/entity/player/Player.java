package runasstrive.model.cards.entity.player;

import runasstrive.io.resources.Messages;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.dice.Die;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player extends Entity<CharacterType> {
    private final int maxHp;
    private final int heal;
    private final int minAbilityCards ;
    protected static int MIN_FP = 1;
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
        return String.format(Messages.PLAYER_STATS, this.getName(), this.getHp(), this.getFp(), this.maxFp);
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
        super.focus(Math.min(this.maxFp - this.fp, fp));
    }

    public boolean canHeal() {
        return this.hp < maxHp  && this.abilities.size() > minAbilityCards;
    }

    public void upgradeCharacterCards() {
        this.abilities.addAll(this.type.getUpgraded());
    }

    public void addAbility(Ability ability) {
        this.abilities.addLast(ability);
    }

    public void heal(List<Integer> choices) {
        List<Ability> toRemove = new ArrayList<>();
        for (Integer choice : choices) {
            this.gainedHealth += Math.min(maxHp - this.hp, heal);
            this.hp += Math.min(maxHp - this.hp, heal);
            toRemove.add(this.abilities.get((choice)));
        }
        this.abilities.removeAll(toRemove);
    }

    public int getMaxHealNumber() {
        int maxHealNumber = 0;
        while (this.getHp() + (maxHealNumber * this.getHeal()) < this.getMaxHp()) {
            maxHealNumber++;
        }
        return maxHealNumber;
    }

    public void increaseFp(Die currentDie) {
        this.maxFp = currentDie.getSides();
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getHeal() {
        return heal;
    }

    public void clearGainedHealth() {
        this.gainedHealth = MIN_HP;
    }

    @Override
    public void useFp(int cost) {
        this.fp = Math.max(MIN_FP, this.fp - cost);
    }

    public int getFocusLevel() {
        Ability card = this.abilities.stream()
                .filter(ability -> ability.getName().equals("Focus"))
                .findFirst().orElse(null);
        return card == null ? 0 : card.getLevel();
    }
}
