package runasstrive.model.dice;

import java.util.Stack;

public class DieBag {
    private final Stack<Die> dice;

    public DieBag(Stack<Die> dice) {
        this.dice = dice;
    }

    public Die getNextDie() {
        return this.dice.pop();
    }

}
