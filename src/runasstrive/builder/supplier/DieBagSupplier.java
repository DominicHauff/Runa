package runasstrive.builder.supplier;

import runasstrive.model.dice.Die;

import java.util.Stack;

/**
 * This class is a pure utility class used to provide
 * all possible {@link Die} objects which the player
 * can upgrade to during the game.
 *
 * @author ugget
 * @version 1.0
 */
public class DieBagSupplier {

    /**
     * This method returns a 'die bag' from
     * which the player receives new dice.
     *
     * @return the stack holding the dice
     */
    public static Stack<Die> getDieBag() {
        final Stack<Die> dieBag = new Stack<>();
        dieBag.push(Die.D12);
        dieBag.push(Die.D10);
        dieBag.push(Die.D8);
        dieBag.push(Die.D6);
        dieBag.push(Die.D4);
        return dieBag;
    }
}
