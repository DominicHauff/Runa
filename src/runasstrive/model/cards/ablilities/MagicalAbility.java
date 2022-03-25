package runasstrive.model.cards.ablilities;

public abstract class MagicalAbility extends Ability {


    protected MagicalAbility(String name, int level, int cost, boolean requiresTarget, boolean requiresDieRoll) {
        super(name, level, cost, requiresTarget, requiresDieRoll);
    }
}
