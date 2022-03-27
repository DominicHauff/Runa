package runasstrive.model;

import runasstrive.builder.supplier.DeckSupplier;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.dice.Die;
import runasstrive.model.levels.FightLog;
import runasstrive.model.levels.GameLevel;
import runasstrive.model.levels.Stage;

import java.util.*;

public class RunasStrive {
    private final Player player;
    private final Stack<Die> dieBag;
    private final Stack<GameLevel> levels;
    private final LinkedList<Ability> reward;
    private List<Ability> deck;

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

    public boolean pickCard(int choice) {
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

    public void advanceToNextLevel() {
        this.levels.pop();
        this.deck = DeckSupplier.getDeck(this.getCurrentLevel().getLevel().getValue());
        this.reward.clear();
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
        return this.dieBag.size() != 1;
    }

    public boolean isLevelCleared() {
        return this.getCurrentLevel().cleared();
    }

    public boolean canPlayerHeal() {
        return this.player.canHeal();
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

    public void upgradeDie() {
        this.dieBag.pop();
        this.player.increaseMaxFp(this.getCurrentDie());
    }

    public int getNumRewardCards() {
        return (int) Math.ceil(((double) this.reward.size()) / 2);
    }

    public void discardLeftOverReward() {
        this.deck.removeAll(reward);
        this.reward.clear();
    }

    public void healPlayer(List<Integer> cardsToRemove)  {
        this.player.heal(cardsToRemove);
    }

    public boolean chooseCharacterType(int choice) {
        final CharacterType type = CharacterType.getCharacterType(choice);
        if (type != null) {
            this.player.setType(type);
            return true;
        }
        return false;
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

    public List<Ability> getRewards() {
        return this.reward;
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
}
