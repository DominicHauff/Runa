package runasstrive.model;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.dice.Die;
import runasstrive.model.levels.GameLevel;

import java.util.*;

public class RunasStrive {
    private final Player player;
    private final List<Ability> deck;
    private final Stack<Die> dieBag;
    private final Stack<GameLevel> levels;
    private final LinkedList<Ability> reward;

    public RunasStrive(Stack<Die> dieBag, List<Ability> abilities, Player player, Stack<GameLevel> levels) {
        this.dieBag = dieBag;
        this.deck = abilities;
        this.player = player;
        this.levels = levels;
        this.reward = new LinkedList<>();
    }

    public GameLevel shuffleCards(int firstSeed, int secondSeed) {
        removeTypeAbilities();
        Collections.shuffle(this.deck, new Random(firstSeed));
        this.levels.peek().initialize(secondSeed);
        return this.levels.peek();
    }

    private void removeTypeAbilities() {
        List<Ability> toRemove = new ArrayList<>();
        this.deck.forEach(ability -> {
            if (ability.getName().equals(this.player.getType().getTypeAbilities().get(0).getName()) ||
                    ability.getName().equals(this.player.getType().getTypeAbilities().get(1).getName())) {
                toRemove.add(ability);
            }
        });
        this.deck.removeAll(toRemove);
    }

    public Ability pickCard(int choice) {
        return this.player.chooseCard(choice);
    }

    public boolean rollDie(int dieRes) {
        this.player.setDieRes(dieRes);
        return this.getCurrentDie().throwDie(dieRes);
    }

    public boolean pickTarget(int choice) {
        if (this.getPossibleTargets().size() <= choice) return false;
        this.player.setTarget(this.getPossibleTargets().get(choice));
        return true;
    }

    public void startFight() {
        this.getCurrentLevel().resume(this.player);
        if (!this.isLevelCleared() && this.stageCleared()) {
            if (this.reward.size() < this.getCurrentLevel().getCurrentStage().getMonsters().size() * 2) {
                for (int i = this.reward.size(); i < this.getCurrentLevel().getCurrentStage().getMonsters().size() * 2; i++) { //TODO: wtf
                    if (i < this.deck.size()) this.reward.addLast(this.deck.get(i));
                }
            }
        }
    }

    public void advanceToNextLevel() {
        this.levels.pop();
    }

    public void advanceToNextStage() {
        this.getCurrentLevel().enterNextStage();
    }

    public boolean gameWon() {
        return this.getCurrentLevel().getLevel().equals(Level.MAX_LEVEL) && this.getCurrentLevel().cleared();
    }

    public boolean gameOver() {
        return this.player.isDead();
    }

    public String getFightLog() {
        return this.getCurrentLevel().getCurrentStage().getFightLog();
    }

    public boolean requiresTargetChoice() {
        final int numberOfAliveTargets = this.getPossibleTargets().size();
        return numberOfAliveTargets > 1;
    }

    public List<Monster> getPossibleTargets() {
        return new ArrayList<>(this.getCurrentLevel().getCurrentStage().getAliveMonsters());
    }

    public Ability drawCard(int choice) {
        if (choice < this.reward.size()) {
            this.player.addAbility(this.reward.get(choice));
            return this.reward.get(choice);
        }
        return null;
    }

    public boolean canChooseDie() {
        return !this.dieBag.isEmpty();
    }

    public Player getPlayer() {
        return player;
    }

    public GameLevel getCurrentLevel() {
        return this.levels.peek();
    }

    public Ability getCardToPlay() {
        return this.player.getCardToPlay();
    }

    public Die getCurrentDie() {
        return this.dieBag.peek();
    }

    public boolean isLevelCleared() {
        return this.getCurrentLevel().cleared();
    }

    public boolean canPlayerHeal() {
        return this.player.canHeal();
    }

    public int getMaxHealNumber() {
        return this.player.getMaxHealNumber();
    }

    public int getPlayerGainedHp() {
        int gainedHp = this.player.getGainedHp();
        this.player.clearGainedHealth();
        return gainedHp;
    }

    public void upgradeCards() {
        this.player.upgradeCharacterCards();
    }

    public boolean stageCleared() {
        return this.getCurrentLevel().getCurrentStage().cleared();
    }

    public Die upgradeDie() {
        this.dieBag.pop();
        this.player.increaseFp(this.getCurrentDie());
        return this.getCurrentDie();
    }

    public int getNumRewardCards() {
        return this.getCurrentLevel().getCurrentStage().getMonsters().size();
    }

    public void discardLeftOverReward() {
        this.deck.removeAll(reward);
        this.reward.clear();
    }

    public List<Ability> getRewards() {
        return this.reward;
    }

    public void healPlayer(List<Integer> cardsToRemove)  {
        this.player.heal(cardsToRemove);
    }

    public void chooseCharacterType(CharacterType type) {
        this.player.setType(type);
    }
}
