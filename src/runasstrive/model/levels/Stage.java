package runasstrive.model.levels;

import runasstrive.model.cards.entity.monster.Monster;

import java.util.Collection;


public class Stage {
    private final Collection<Monster> monsters;

    public Stage(Collection<Monster> monsters) {
        this.monsters = monsters;
    }

    public boolean cleared() {
        return this.monsters.isEmpty();
    }
}
