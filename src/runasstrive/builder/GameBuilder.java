package runasstrive.builder;

import runasstrive.DeckSupplier;
import runasstrive.DieSupplier;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.dice.Die;
import runasstrive.model.dice.DieBag;

import java.util.Stack;

public class GameBuilder {
    private final DieSupplier dieSupplier;
    private final DeckSupplier deckSupplier;
    private final Stack<Die> dice;
    private final DieBag dieBag;
    private final Stack<Ability> deck;
    //private final Player player;

    public GameBuilder() {
        this.deckSupplier = new DeckSupplier();
        this.dieSupplier = new DieSupplier();
        this.dice = dieSupplier.getDice();
        this.dieBag = new DieBag(this.dice);
        this.deck = deckSupplier.getDeck();
        //this.player = new Player(Level.LEVEL_ONE, 50);
    }

    public RunasStrive buildGame() {
        //TODO: game levels, player argument
        return new RunasStrive(this.dieBag, this.deck, null, null);
    }
}
