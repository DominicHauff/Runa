package runasstrive.model;

import runasstrive.builder.supplier.DeckSupplier;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.dice.Die;
import runasstrive.model.levels.FightLog;
import runasstrive.model.levels.GameLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents the game which
 * holds all methods used to perform game actions and
 * manipulate entities.
 *
 * @author ugget
 * @version 1.0
 */
public class RunasStrive {
    private final Player player;
    private final Stack<Die> dieBag;
    private final Stack<GameLevel> levels;
    private final LinkedList<Ability> reward;
    private List<Ability> deck;

    /**
     * This method constructs a new RunasStrive object.
     *
     * @param dieBag the die bag which holds all dice
     * @param abilities the deck holding all choosable ability cards
     * @param player the player, in this case Runa
     * @param levels all game levels
     */
    public RunasStrive(Stack<Die> dieBag, List<Ability> abilities, Player player, Stack<GameLevel> levels) {
        this.dieBag = dieBag;
        this.deck = abilities;
        this.player = player;
        this.levels = levels;
        this.reward = new LinkedList<>();
    }

    /**
     * This method shuffles the ability card deck and the monster card deck.
     *
     * @param firstSeed the seed used to shuffle the ability cards
     * @param secondSeed the seed used to shuffle the monster cards
     */
    public void shuffleCards(int firstSeed, int secondSeed) {
        removeTypeAbilities();
        Collections.shuffle(this.deck, new Random(firstSeed));
        this.levels.peek().initialize(secondSeed);
    }

    /**
     * This method returns a card from the player's hand chosen by the user
     *
     * @param choice the card index
     * @return the chosen card
     */
    public boolean pickCard(int choice) {
        return this.player.chooseCard(choice);
    }

    /**
     * This method returns a boolean based on whether the given
     * die result is valid or not.
     *
     * @param dieRes the given die roll result
     * @return whether the given die result is valid
     */
    public boolean rollDie(int dieRes) {
        this.player.setDieRes(dieRes);
        return this.getCurrentDie().throwDie(dieRes);
    }

    /**
     * This method returns a boolean based on whether the given
     * target choice index is valid.
     *
     * @param choice the given target choice index
     * @return whether the index is valid
     */
    public boolean pickTarget(int choice) {
        if (this.getPossibleTargets().size() <= choice) return false;
        this.player.setTarget(this.getPossibleTargets().get(choice));
        return true;
    }

    /**
     * This method returns a FightLog object which holds
     * all information about the latest fight.
     *
     * @return the fight log
     */
    public FightLog startFight() {
        final FightLog fightLog = this.getCurrentLevel().resume(this.player);
        if (fightLog.getStage().cleared()) {
            this.advanceToNextStage();
            if (!this.isLevelCleared()) {
                if (this.reward.size() < fightLog.getStage().getMonsters().size() * 2) {
                    int maxRewardNum = Math.min(
                            this.deck.size(),
                            fightLog.getStage().getMonsters().size() * 2
                    );
                    for (int i = this.reward.size(); i < maxRewardNum; i++) { //TODO: wtf
                        if (i < this.deck.size()) this.reward.addLast(this.deck.get(i));
                    }
                }
                fightLog.setFightResult(FightResult.STAGE_CLEARED);
                return fightLog;
            }
        }

        if (this.gameOver()) {
            fightLog.setFightResult(FightResult.GAME_OVER);
            return fightLog;
        }
        if (this.gameWon()) {
            fightLog.setFightResult(FightResult.GAME_WON);
            return fightLog;
        }
        if (this.isLevelCleared()) {
            this.advanceToNextLevel();
            this.upgradeCards();
            fightLog.setFightResult(FightResult.LEVEL_CLEARED);
            return fightLog;
        }
        fightLog.setFightResult(FightResult.CONTINUE);
        return fightLog;
    }

    /**
     * This method updates the current level when the last stage
     * of the previous level has been cleared
     */
    public void advanceToNextLevel() {
        this.levels.pop();
        this.deck = DeckSupplier.getDeck(this.getCurrentLevel().getLevel().getValue());
        this.reward.clear();
    }

    /**
     * This method updated the current stage when the previous stage
     * has been cleared
     */
    public void advanceToNextStage() {
        this.getCurrentLevel().enterNextStage();
    }

    /**
     * This method returns a boolean based on whether
     * Runa has won the game.
     *
     * @return whether Runa has won the game
     */
    public boolean gameWon() {
        return this.getCurrentLevel().getLevel().equals(Level.MAX_LEVEL) && this.getCurrentLevel().cleared();
    }

    /**
     * This method returns a boolean based on whether
     * Runa has died.
     *
     * @return whether Runa has died
     */
    public boolean gameOver() {
        return this.player.isDead();
    }

    /**
     * This method returns a boolean based on whether there are
     * more than one alive monsters and thus whether a target choice
     * is required during a fight.
     *
     * @return whether there are multiple targets
     */
    public boolean requiresTargetChoice() {
        final int numberOfAliveTargets = this.getPossibleTargets().size();
        return numberOfAliveTargets > 1;
    }

    /**
     * This method returns a list of all possible targets
     * during a fight.
     *
     * @return possible targets
     */
    public List<Monster> getPossibleTargets() {
        return new ArrayList<>(this.getCurrentLevel().getCurrentStage().getAliveMonsters());
    }

    /**
     * This method returns a chosen ability card if the
     * card index is valid.
     *
     * @param choice card index
     * @return chosen ability
     */
    public Ability drawCard(int choice) {
        if (choice < this.reward.size()) {
            this.player.addAbility(this.reward.get(choice));
            return this.reward.get(choice);
        }
        return null;
    }

    /**
     * This method returns a boolean based on whether
     * the player can upgrade their die.
     *
     * @return whether die can be upgraded
     */
    public boolean canChooseDie() {
        return this.dieBag.size() != 1;
    }

    /**
     * This method returns a boolean based on whether
     * the current level is cleared.
     *
     * @return whether the level is cleared
     */
    public boolean isLevelCleared() {
        return this.getCurrentLevel().cleared();
    }

    /**
     * This method returns a boolean based on
     * whether the player can heal.
     *
     * @return whether player can heal
     */
    public boolean canPlayerHeal() {
        return this.player.canHeal();
    }

    /**
     * This method returns the amount of gained hp
     * after the player healed.
     *
     * @return amount of hp
     */
    public int getPlayerGainedHp() {
        int gainedHp = this.player.getGainedHp();
        this.player.clearGainedHealth();
        return gainedHp;
    }

    /**
     * This method adds the upgraded versions of the
     * character class abilities to the players abilities.
     */
    public void upgradeCards() {
        this.player.upgradeCharacterCards();
    }

    /**
     * This method adds the next better die to the player.
     */
    public void upgradeDie() {
        this.dieBag.pop();
        this.player.increaseMaxFp(this.getCurrentDie());
    }

    /**
     * This method returns an integer representing the number of cards
     * the player can choose as a reward after a fight.
     *
     * @return number of cards
     */
    public int getNumRewardCards() {
        return (int) Math.ceil(((double) this.reward.size()) / 2);
    }

    /**
     * This method removes all leftover reward cards
     * from the deck.
     */
    public void discardLeftOverReward() {
        this.deck.removeAll(reward);
        this.reward.clear();
    }

    /**
     * This method heals the player for every ability card in
     * the given list.
     *
     * @param cardsToRemove ability cards that get discarded
     */
    public void healPlayer(List<Integer> cardsToRemove)  {
        this.player.heal(cardsToRemove);
    }

    /**
     * This method assigns a chosen character class to the player
     * and returns a boolean based on whether the choice index is valid.
     *
     * @param choice choice index
     * @return whether the index is valid
     */
    public boolean chooseCharacterType(int choice) {
        final CharacterType type = CharacterType.getCharacterType(choice);
        if (type != null) {
            this.player.setType(type);
            return true;
        }
        return false;
    }

    /**
     * This method returns a reference to the games Player object
     *
     * @return a reference to the player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * This method returns the current game level.
     *
     * @return current game level
     */
    public GameLevel getCurrentLevel() {
        return this.levels.peek();
    }

    /**
     * This method returns the ability which the
     * player is going to play.
     *
     * @return the player's ability to play
     */
    public Ability getCardToPlay() {
        return this.player.getCardToPlay();
    }

    /**
     * This method returns the player's current die.
     *
     * @return current die
     */
    public Die getCurrentDie() {
        return this.dieBag.peek();
    }

    /**
     * This method returns a list of reward cards from which the player
     * can choose after a fight.
     *
     * @return reward cards
     */
    public List<Ability> getRewards() {
        return this.reward;
    }

    private void removeTypeAbilities() {
        List<Ability> toRemove = new ArrayList<>();
        this.deck.forEach(ability -> {
            if (ability.getName().equals(this.player.getType().getTypeAbilities().get(0).getName())
                    || ability.getName().equals(this.player.getType().getTypeAbilities().get(1).getName())) {
                toRemove.add(ability);
            }
        });
        this.deck.removeAll(toRemove);
    }
}
