package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.physical.defensive.Block;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Bite;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Scratch;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Tarantula extends Monster {
    private static final String NAME = "Tarantula";
    private static final int HP = 33;
    private static final int BITE_LEVEL = 2;
    private static final int BLOCK_LEVEL = 2;
    private static final int SCRATCH_LEVEL = 2;
    private static final List<Ability> ABILITIES = List.of(
            new Bite(BITE_LEVEL),
            new Block(BLOCK_LEVEL),
            new Scratch(SCRATCH_LEVEL)
    );

    public Tarantula() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        //TODO: remove
        return 0;
    }
}
