package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Lightning;
import runasstrive.model.cards.ablilities.physical.defensive.Block;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Bite;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class SpiderKing extends Monster {
    private static final String NAME = "Spider King";
    private static final int HP = 50;
    private static final int BITE_LEVEL = 1;
    private static final int BLOCK_LEVEL = 1;
    private static final int FOCUS_LEVEL = 1;
    private static final int LIGHTNING_LEVEL = 1;
    private static final List<Ability> ABILITIES = List.of(
            new Bite(BITE_LEVEL),
            new Block(BLOCK_LEVEL),
            new Focus(FOCUS_LEVEL),
            new Lightning(LIGHTNING_LEVEL, LIGHTNING_LEVEL)
    );

    public SpiderKing() {
        super(NAME, HP, MonsterType.LIGHTNING, ABILITIES);
    }
}
