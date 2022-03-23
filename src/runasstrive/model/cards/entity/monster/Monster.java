package runasstrive.model.cards.entity.monster;

import runasstrive.io.resources.Messages;
import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.LinkedList;
import java.util.List;

public abstract class Monster extends Entity<MonsterType> {

    public Monster(String name, int hp, Level level, MonsterType type, List<Ability> abilities) {
        super(level, name, hp, abilities);
        this.hp = hp;
        this.setType(type);
    }

    @Override
    public String toString() {
        return String.format(Messages.MONSTER_STATS,
                this.getName(), this.getHp(), this.getFp(), this.abilities.getFirst().toString());
    }
}
