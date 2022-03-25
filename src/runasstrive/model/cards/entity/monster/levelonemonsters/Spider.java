package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.physical.defensive.Block;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Bite;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Spider extends Monster {
    private static final String NAME = "Spider";
    private static final int HP = 15;
    private static final int BITE_LEVEL = 1;
    private static final int BLOCK_LEVEL = 1;
    private static final List<Ability> ABILITIES = List.of(new Bite(BITE_LEVEL), new Block(BLOCK_LEVEL));

    public Spider() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }
}
