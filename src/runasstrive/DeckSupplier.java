package runasstrive;

import runasstrive.model.cards.ablilities.Ability;

import java.util.Stack;

public class DeckSupplier {
    private final Stack<Ability> deck;

    public DeckSupplier() {
        this.deck = new Stack<>();
        this.setDeck();
    }

    private void setDeck() {
        //TODO: push abilities, shove them all in
    }

    public Stack<Ability> getDeck() {
        return this.deck;
    }
}
