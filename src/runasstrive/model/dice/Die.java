package runasstrive.model.dice;

public class Die {
    private static final String ID = "d";
    private final int sides;

    public Die(int sides) {
        this.sides = sides;
    }

    public int throwDie() {
        //TODO: implement
        return 0;
    }

    public String getID() {
        return ID + this.sides;
    }
}
