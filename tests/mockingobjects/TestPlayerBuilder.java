package mockingobjects;

import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;

import java.util.List;

public class TestPlayerBuilder {
    private Level level;
    private String name;
    private int hp;
    private List<Ability> abilities;

    public TestPlayerBuilder setLevel(Level level) {
        this.level = level;
        return this;
    }

    public TestPlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TestPlayerBuilder setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public TestPlayerBuilder setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    public TestPlayer createTestPlayer() {
        return new TestPlayer(level, name, hp, abilities);
    }
}