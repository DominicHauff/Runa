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
    private static final int COST = 1;

    public static List<Ability> getDeck(int level) {
        final List<Ability> deck = new ArrayList<>();
        deck.add(new Slash(level));
        deck.add(new Swing(level));
        deck.add(new Thrust(level));
        deck.add(new Pierce(level));
        deck.add(new Parry(level));
        deck.add(new Focus(level));
        deck.add(new Reflect(level));
        deck.add(new Water(level, COST));
        deck.add(new Ice(level, COST));
        deck.add(new Fire(level, COST));
        deck.add(new Lightning(level, COST));

        return deck;
    }

}
