package runasstrive.model.cards.entity.player;

import runasstrive.io.resources.Messages;
import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.type.CharacterType;

import java.util.Collection;
import java.util.Collections;

public class Player extends Entity<CharacterType> {
    private Ability cardToPlay;
    public Player(String name, Level level, int hp) {
        super(level, name, hp, Collections.emptyList());
    }

    @Override
    public String toString() {
        return String.format(Messages.PLAYER_STATS, this.getName(), this.getHp(), this.getFp());
    }

    public void chooseCard(int index) {
        this.cardToPlay = this.getAbilities().size() <= index ? null : this.getAbilities().get(index);
    }

    public Ability getCardToPlay() {
        return cardToPlay;
    }
}
