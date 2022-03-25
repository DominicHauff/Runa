package runasstrive.builder.supplier;

import runasstrive.model.dice.Die;

import java.util.Stack;

public class DieBagSupplier {

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
