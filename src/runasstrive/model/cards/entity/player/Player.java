package runasstrive.model.cards.entity.player;

import runasstrive.view.resources.Messages;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.dice.Die;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents the player, in this case
 * Runa as she is the game's only Player object.
 *
 * @author ugget
 * @version 1.0
 */
public class Player extends Entity<CharacterType> {

    /**
     * the player's minimal amount of focus points
     */
    protected static final int MIN_FP = 1;
    private final int maxHp;
    private final int heal;
    private final int minAbilityCards;
    private int maxFp;
    private Ability cardToPlay;
    private Monster target;
    private int dieRes;

    /**
     * constructs a new Player object
     *
     * @param name the player's name
     * @param heal the amount of health points a player gains for discarding one ability
     * @param minAbilityCards the minimal amount of ability cards a player holds
     * @param hp the player's initial amount of health points
     * @param fp the player's initial amount of focus points
     * @param maxFp the player's maximum amount of health points
     * @param abilities the player's ability cards
     */
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

    /**
     * @return returns the ability the player is currently playing
     */
    public Ability getCardToPlay() {
        return this.cardToPlay;
    }

    /**
     * @return returns the current target if chosen
     */
    public Monster getTarget() {
        return this.target;
    }

    @Override
    public void setType(CharacterType type) {
        super.setType(type);
        this.abilities.addAll(type.getTypeAbilities());
    }

    /**
     * @param target the chosen target for the next attack
     */
    public void setTarget(Monster target) {
        this.target = target;
    }

    /**
     * @return returns the result of the latest die roll
     */
    public int getDieRes() {
        return dieRes;
    }

    /**
     * @param dieRes the entered die roll result
     */
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

    /**
     * @return returns a boolean based on whether the player is able to heal
     * depending on the amount of ability cards and health points
     */
    public boolean canHeal() {
        return this.hp < maxHp  && this.abilities.size() > minAbilityCards;
    }

    /**
     * adds the upgraded versions of the character type's default abilities
     * to the player's deck of ability cards
     */
    public void upgradeCharacterCards() {
        this.abilities.addAll(this.type.getUpgraded());
    }

    /**
     * @param ability an ability that's added to the player's deck of abilities
     */
    public void addAbility(Ability ability) {
        this.abilities.addLast(ability);
    }

    /**
     * increases the player's health points by discarding ability cards from the player's deck
     *
     * @param choices a list of indices representing the player's chosen abilities to discard
     *                when healing
     */
    public void heal(List<Integer> choices) {
        List<Ability> toRemove = new ArrayList<>();
        for (Integer choice : choices) {
            this.gainedHealth += Math.min(maxHp - this.hp, heal);
            this.hp += Math.min(maxHp - this.hp, heal);
            toRemove.add(this.abilities.get((choice)));
        }
        this.abilities.removeAll(toRemove);
    }

    /**
     * increases the maximum amount of focus points according to the
     *
     * @param currentDie
     */
    public void increaseMaxFp(Die currentDie) {
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
