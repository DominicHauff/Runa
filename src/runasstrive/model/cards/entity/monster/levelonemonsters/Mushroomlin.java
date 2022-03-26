package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Deflect;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Scratch;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Mushroomlin extends Monster {
    private static final String NAME = "Mushroomlin";
    private static final int HP = 20;
    private static final int DEFLECT_LEVEL = 1;
    private static final int SCRATCH_LEVEL = 1;
    private static final List<Ability> ABILITIES = List.of(new Deflect(DEFLECT_LEVEL), new Scratch(SCRATCH_LEVEL));

    public Mushroomlin() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        //TODO: bruh
        return 0;
    }
}
