package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.physical.defensive.Block;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Claw;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Rat extends Monster {
    private static final String NAME = "Rat";
    private static final int HP = 14;
    private static final int BLOCK_LEVEL = 1;
    private static final int CLAW_LEVEL = 1;
    private static final List<Ability> ABILITIES = List.of(new Block(BLOCK_LEVEL), new Claw(CLAW_LEVEL));

    public Rat() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }
}
