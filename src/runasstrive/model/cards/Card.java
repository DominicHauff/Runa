package runasstrive.model.cards;

import runasstrive.model.Level;

public abstract class Card {
    //TODO: add attributes like name and methods like toString
    protected final Level level;

    protected Card(Level level) {
        this.level = level;
    }
}
