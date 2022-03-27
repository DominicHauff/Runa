package runasstrive.model.cards;

/**
 * This class represents an abstract card, it serves as a super class
 * for {@link runasstrive.model.cards.entity.Entity} objects and
 * {@link runasstrive.model.cards.ablilities.Ability} objects.
 *
 * @author ugget
 * @version 1.0
 */
public abstract class Card {

    /**
     * the card's name
     */
    protected final String name;

    /**
     * super constructor for all classes extending Card
     *
     * @param name the cards name
     */
    protected Card(String name) {
        this.name = name;
    }

    /**
     * @return returns the cards name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return returns the cards name and additional information as a String
     */
    public abstract String toString();
}
