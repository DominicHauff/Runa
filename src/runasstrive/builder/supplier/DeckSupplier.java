package runasstrive.builder.supplier;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.defensive.Reflect;
import runasstrive.model.cards.ablilities.magic.offensive.Fire;
import runasstrive.model.cards.ablilities.magic.offensive.Ice;
import runasstrive.model.cards.ablilities.magic.offensive.Lightning;
import runasstrive.model.cards.ablilities.magic.offensive.Water;
import runasstrive.model.cards.ablilities.physical.defensive.Parry;
import runasstrive.model.cards.ablilities.physical.offensive.player.Pierce;
import runasstrive.model.cards.ablilities.physical.offensive.player.Slash;
import runasstrive.model.cards.ablilities.physical.offensive.player.Swing;
import runasstrive.model.cards.ablilities.physical.offensive.player.Thrust;

import java.util.Stack;

public class DeckSupplier {
    private static final int INITIAL_LEVEL = 1;
    private static final int COST = 1;
    public static Stack<Ability> getDeck() {
        final Stack<Ability> deck = new Stack<>();
        deck.push(new Slash(INITIAL_LEVEL));
        deck.push(new Swing(INITIAL_LEVEL));
        deck.push(new Thrust(INITIAL_LEVEL));
        deck.push(new Pierce(INITIAL_LEVEL));
        deck.push(new Parry(INITIAL_LEVEL));
        deck.push(new Focus(INITIAL_LEVEL));
        deck.push(new Reflect(INITIAL_LEVEL));
        deck.push(new Water(INITIAL_LEVEL, COST));
        deck.push(new Ice(INITIAL_LEVEL, COST));
        deck.push(new Fire(INITIAL_LEVEL, COST));
        deck.push(new Lightning(INITIAL_LEVEL, COST));
        return deck;
    }
}
