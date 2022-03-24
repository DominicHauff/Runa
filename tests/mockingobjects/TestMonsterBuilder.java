package mockingobjects;

import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class TestMonsterBuilder {
    private String name;
    private int hp;
    private Level level;
    private MonsterType type;
    private List<Ability> abilities;

    public TestMonsterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public TestMonsterBuilder setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public TestMonsterBuilder setLevel(Level level) {
        this.level = level;
        return this;
    }

    public TestMonsterBuilder setType(MonsterType type) {
        this.type = type;
        return this;
    }

    public TestMonsterBuilder setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
        return this;
    }

    public TestMonster createTestMonster() {
        return new TestMonster(name, hp, level, type, abilities);
    }
}