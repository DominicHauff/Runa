package runasstrive.model.dice;

/**
 * This enum holds constants defining a die. Each die is defined by its number of sides.
 *
 * @author ugget
 * @version 1.0
 */
public enum Die {

    /**
     * The d4 die with 4 sides.
     */
    D4(4, "d4"),

    /**
     * The d6 die with 6 sides.
     */
    D6(6, "d6"),

    /**
     * The d8 die with 8 sides.
     */
    D8(8, "d8"),

    /**
     * The d10 die with 10 sides.
     */
    D10(10, "d10"),

    /**
     * The d12 die with 12 sides.
     */
    D12(12, "d12");

    private static final int MIN = 1;
    private final int sides;
    private final String id;

    /**
     * The enum constructor enhancing this enum with a sides and an id attribute.
     *
     * @param sides the number of sides the die has
     * @param id the string representation of the die
     */
    Die(int sides, String id) {
        this.sides = sides;
        this.id = id;
    }

    /**
     * This method is used to simulate throwing a die.
     * Its purpose is to check whether the provided die result from the IO layer was faulty.
     *
     * @param side the provided die result of the IO layer. Might be out of range for this die
     * @return {@code false} if the side is not included in the range of the number, {@code true} else.
     */
    public boolean throwDie(int side) {
        return side >= MIN && side <= this.sides;
    }

    /**
     * @return the number of sides this die has.
     */
    public int getSides() {
        return this.sides;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
