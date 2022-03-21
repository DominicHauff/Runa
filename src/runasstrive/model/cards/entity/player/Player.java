package runasstrive.model.cards.entity.player;

import runasstrive.model.Level;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.CharacterType;

public abstract class Player extends Entity<CharacterType> {

    protected Player(Level level, CharacterType type, int hp) {
        super(level, type, hp);
    }

}
