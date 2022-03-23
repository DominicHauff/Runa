package runasstrive.model.cards.entity.player;

import runasstrive.io.resources.Messages;
import runasstrive.model.Level;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.CharacterType;

import java.util.Collection;
import java.util.Collections;

public class Player extends Entity<CharacterType> {
    public Player(String name, Level level, int hp) {
        super(level, name, hp, Collections.emptyList());
    }

    @Override
    public String toString() {
        return String.format(Messages.PLAYER_STATS, this.getName(), this.getHp(), this.getFp());
    }
}
