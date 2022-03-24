package runasstrive.model.dice;

public enum Die {
    D4(4, "d4"), D6(6, "d6"), D8(8, "d8"), D10(10, "d10"), D12(12, "d12");

    private static final int MIN = 1;
    private final int sides;
    private final String id;

    Die(int sides, String id) {
        this.sides = sides;
        this.id = id;
    }

    public boolean throwDie(int side) {
        return side >= MIN && side <= this.sides;
    }

    @Override
    public String toString() {
        return this.id;
    }

    public int getSides() {
        return this.sides;
    }
}
