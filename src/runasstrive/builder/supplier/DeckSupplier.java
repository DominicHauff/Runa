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

/**
 * This class is a pure utility class used to provide a complete
 * deck of all {@link Ability} cards which can be used by the
 * player
 *
 * @author ugget
 * @version 1.0
 */
public class DeckSupplier {
    private static final int COST = 1;

    /**
     * This method returns the ability deck from
     * which the player can choose new abilities
     * as rewards after won battles.
     *
     * @param level the level with which each card in the deck is initialized
     * @return the deck
     */
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
