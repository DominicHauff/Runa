package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Deflect;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Scratch;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class WildBoar extends Monster {
    private static final String NAME = "Wild Boar";
    private static final int HP = 27;
    private static final int SCRATCH_LEVEL = 2;
    private static final int DEFLECT_LEVEL = 2;
    private static final List<Ability> ABILITIES = List.of(
            new Scratch(SCRATCH_LEVEL),
            new Deflect(DEFLECT_LEVEL)
    );

    public WildBoar() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        //TODO: TODO
        return 0;
    }
}
