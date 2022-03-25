package runasstrive.builder;

import runasstrive.model.dice.Die;

import java.util.Stack;

public class DieSupplier {
    private final Stack<Die> dice;

    public DieSupplier() {
        this.dice = new Stack<>();
        this.setDice();
    }

    private void setDice() {
        /*this.dice.push(new Die(4));
        this.dice.push(new Die(6));
        this.dice.push(new Die(8));
        this.dice.push(new Die(10));
        this.dice.push(new Die(12));*/
    }

    public Stack<Die> getDice() {
        return dice;
    }
}
