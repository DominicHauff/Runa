package runasstrive.model;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.dice.Die;
import runasstrive.model.dice.DieBag;
import runasstrive.model.levels.GameLevel;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class RunasStrive {
    private final DieBag dieBag;
    private final Stack<Ability> deck;
    private final Player player;
    private final Stack<GameLevel> levels;
    private Die die;

    public RunasStrive(DieBag dieBag, Stack<Ability> abilities, Player player, Stack<GameLevel> levels) {
        this.dieBag = dieBag;
        this.deck = abilities;
        this.player = player;
        this.levels = levels;
    }

    public GameLevel shuffleCards(int firstSeed, int secondSeed) {
        Collections.shuffle(this.deck, new Random(firstSeed));
        this.levels.peek().initialize(secondSeed);
        return this.levels.peek();
    }

    public void playCard(int cardIndex) {
        //TODO: implement
    }

    public void rollDie(int dieRes) {

    }

    public void drawCard() {

    }

    public void getNextDie() {
        //TODO: complete implementation
        this.die = this.dieBag.getNextDie();
    }

    public Player getPlayer() {
        return player;
    }

    public GameLevel getCurrentLevel() {
        return this.levels.peek();
    }
}
