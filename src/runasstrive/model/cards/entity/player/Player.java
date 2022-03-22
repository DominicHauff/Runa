package runasstrive.model.cards.entity.player;

import runasstrive.model.Level;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.CharacterType;

public class Player extends Entity<CharacterType> {
    public Player(Level level, int hp) {
        super(level, hp);
    }

}
