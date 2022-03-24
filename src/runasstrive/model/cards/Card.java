package runasstrive.model.cards;

public abstract class Card {
    protected final String name;

    protected Card(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract String toString();
}
