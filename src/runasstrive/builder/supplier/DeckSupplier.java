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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DeckSupplier {
    private static final int INITIAL_LEVEL = 1;
    private static final int COST = 1;
    public static List<Ability> getDeck() {
        // first one is the og deck made by loren
        /**final Stack<Ability> deck = new Stack<>();
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
        deck.push(new Lightning(INITIAL_LEVEL, COST));*/

        final List<Ability> deck = new ArrayList<>();
        deck.add(new Slash(INITIAL_LEVEL));
        deck.add(new Swing(INITIAL_LEVEL));
        deck.add(new Thrust(INITIAL_LEVEL));
        deck.add(new Pierce(INITIAL_LEVEL));
        deck.add(new Parry(INITIAL_LEVEL));
        deck.add(new Focus(INITIAL_LEVEL));
        deck.add(new Reflect(INITIAL_LEVEL));
        deck.add(new Water(INITIAL_LEVEL, COST));
        deck.add(new Ice(INITIAL_LEVEL, COST));
        deck.add(new Fire(INITIAL_LEVEL, COST));
        deck.add(new Lightning(INITIAL_LEVEL, COST));

        return deck;
    }

}
