package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Fire;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Scratch;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Hornet extends Monster {
    private static final String NAME = "Hornet";
    private static final int HP = 32;
    private static final int SCRATCH_LEVEL = 2;
    private static final int FOCUS_LEVEL = 2;
    private static final int FIRE_ONE_LEVEL = 1;
    private static final int FIRE_TWO_LEVEL = 2;
    private static final List<Ability> ABILITIES = List.of(
            new Scratch(SCRATCH_LEVEL),
            new Focus(FOCUS_LEVEL),
            new Fire(FIRE_ONE_LEVEL, FIRE_ONE_LEVEL),
            new Fire(FIRE_TWO_LEVEL, FIRE_TWO_LEVEL)
    );

    public Hornet() {
        super(NAME, HP, MonsterType.FIRE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
