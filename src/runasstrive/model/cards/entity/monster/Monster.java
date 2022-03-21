package runasstrive.model.cards.entity.monster;

import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public abstract class Monster extends Entity<MonsterType> {
    private final List<Ability> attacks;

    public Monster(int hp, Level level, MonsterType type, List<Ability> attacks) {
        super(level, type, hp);
        this.hp = hp;
        this.attacks = attacks;
    }

}
