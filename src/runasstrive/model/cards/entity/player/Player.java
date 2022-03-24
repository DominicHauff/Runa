package runasstrive.model.cards.entity.player;

import runasstrive.io.resources.Messages;
import runasstrive.model.Level;
import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.Entity;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.CharacterType;
import runasstrive.model.dice.Die;

import java.util.Collections;

public class Player extends Entity<CharacterType> {
    private Ability cardToPlay;
    private Monster target;
    private int dieRes;
    private int maxFp;

    public Player(String name, Level level, int hp) {
        super(level, name, hp, Collections.emptyList());
        this.fp = 1;
        this.maxFp = 4;
    }

    @Override
    public String toString() {
        return String.format(Messages.PLAYER_STATS, this.getName(), this.getHp(), this.getFp());
    }

    public Ability chooseCard(int index) {
        this.cardToPlay = this.getAbilities().size() <= index ? null : this.getAbilities().get(index);
        return this.cardToPlay;
    }

    public Ability getCardToPlay() {
        return this.cardToPlay;
    }

    public Monster getTarget() {
        return this.target;
    }

    public void setTarget(Monster target) {
        this.target = target;
    }

    public int getDieRes() {
        return dieRes;
    }

    public void setDieRes(int dieRes) {
        this.dieRes = dieRes;
    }

    @Override
    public Ability nextAbility() {
        return this.cardToPlay;
    }

    @Override
    public void focus(int fp) {
        if (this.maxFp < this.fp + fp) {
            this.fp = this.maxFp;
            return;
        }
        this.fp += fp;
    }
}
