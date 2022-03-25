package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Deflect;
import runasstrive.model.cards.ablilities.physical.defensive.Block;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Claw;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Bear extends Monster {
    private static final String NAME = "Bear";
    private static final int HP = 40;
    private static final int CLAW_LEVEL = 2;
    private static final int DEFLECT_LEVEL = 2;
    private static final int BLOCK_LEVEL = 2;
    private static final List<Ability> ABILITIES = List.of(
            new Claw(CLAW_LEVEL),
            new Deflect(DEFLECT_LEVEL),
            new Block(BLOCK_LEVEL)
    );

    public Bear() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }
}
