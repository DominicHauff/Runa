package mockingobjects;

import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class TestMonster extends Monster {

    public TestMonster(String name, int hp, Level level, MonsterType type, List<Ability> abilities) {
        super(name, hp, level, type, abilities);
    }

}
