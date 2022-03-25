package mockingobjects;

import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.player.Player;
import runasstrive.model.cards.entity.type.CharacterType;

import java.util.List;

public class TestPlayer extends Entity<CharacterType> {


    protected TestPlayer(Level level, String name, int hp, List<Ability> abilities) {
        super(name, hp, abilities);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Ability nextAbility() {
        return null;
    }
}
