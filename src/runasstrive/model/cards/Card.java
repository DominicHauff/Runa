package runasstrive.model.cards;

import runasstrive.model.Level;

public abstract class Card {
    protected final String name;
    protected final Level level;

    protected Card(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public abstract String toString();
}
