package runasstrive.model.cards.entity.monster;

import runasstrive.io.resources.Messages;
import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.LinkedList;
import java.util.List;

public abstract class Monster extends Entity<MonsterType> {
    private final LinkedList<Ability> attacks;

    public Monster(String name, int hp, Level level, MonsterType type, List<Ability> attacks) {
        super(level, name, hp);
        this.hp = hp;
        this.attacks = new LinkedList<>(attacks);
        this.setType(type);
    }

    @Override
    public String toString() {
        return String.format(Messages.MONSTER_STATS,
                this.getName(), this.getHp(), this.getFp(), this.attacks.getFirst().toString());
    }
}
