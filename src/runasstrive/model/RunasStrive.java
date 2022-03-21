package runasstrive.model;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.dice.Die;
import runasstrive.model.dice.DieBag;
import runasstrive.model.levels.GameLevel;

import java.util.Stack;

public class RunasStrive {
    private final DieBag dieBag;
    private final Stack<Ability> deck;
    private final Player runa;
    private GameLevel currentLevel;
    private Die die;

    public RunasStrive(DieBag dieBag, Stack<Ability> deck, Player runa) {
        this.dieBag = dieBag;
        this.deck = deck;
        this.runa = runa;
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
}
