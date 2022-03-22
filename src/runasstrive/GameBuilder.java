package runasstrive;

import runasstrive.model.Level;
import runasstrive.model.RunasStrive;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.player.Runa;
import runasstrive.model.dice.Die;
import runasstrive.model.dice.DieBag;

import java.util.Stack;

public class GameBuilder {
    private final DieSupplier dieSupplier;
    private final DeckSupplier deckSupplier;
    private final Stack<Die> dice;
    private final DieBag dieBag;
    private final Stack<Ability> deck;
    private final Player runa;

    public GameBuilder() {
        this.deckSupplier = new DeckSupplier();
        this.dieSupplier = new DieSupplier();
        this.dice = dieSupplier.getDice();
        this.dieBag = new DieBag(this.dice);
        this.deck = deckSupplier.getDeck();
        this.runa = new Runa(Level.LEVEL_ONE, 50);
    }

    public RunasStrive buildGame() {
        return new RunasStrive(this.dieBag, this.deck, this.runa);
    }
}
