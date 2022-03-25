package mockingobjects;

import runasstrive.model.Level;

public class TestAbilityBuilder {
    private String name;
    private Level level;
    private int cost;
    private boolean requiresTarget;
    private boolean requiresDieRoll;

    public TestAbilityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TestAbilityBuilder setLevel(Level level) {
        this.level = level;
        return this;
    }

    public TestAbilityBuilder setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public TestAbilityBuilder setRequiresTarget(boolean requiresTarget) {
        this.requiresTarget = requiresTarget;
        return this;
    }

    public TestAbilityBuilder setRequiresDieRoll(boolean requiresDieRoll) {
        this.requiresDieRoll = requiresDieRoll;
        return this;
    }

    public TestAbility createTestAbility() {
        return new TestAbility(name, 1, cost, requiresTarget, requiresDieRoll);
    }
}