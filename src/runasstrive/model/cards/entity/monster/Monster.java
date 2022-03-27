package runasstrive.model.cards.entity.monster;

import runasstrive.view.resources.Messages;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public abstract class Monster extends Entity<MonsterType> {
    private Ability currentAbility;

    public Monster(String name, int hp, MonsterType type, List<Ability> abilities) {
        super(name, hp, abilities);
        this.hp = hp;
        this.fp = 0;
        this.setType(type);
    }

    @Override
    public String toString() {
        return String.format(Messages.MONSTER_STATS,
                this.getName(), this.getHp(), this.getFp(), this.abilities.getFirst().toString());
    }

    @Override
    public Ability nextAbility() {
        final Ability nextAbility = this.abilities.stream().filter(ability -> ability.getCost() <= this.getFp())
                .findFirst().orElse(null);
        int index = this.abilities.indexOf(nextAbility);
        for (int i = 0; i <= index; i++) {
            this.abilities.addLast(this.abilities.removeFirst());
        }

        return nextAbility;
    }

    public abstract int getFocusLevel();

    @Override
    public Class<?> getEntityType() {
        return MonsterType.class;
    }
}
