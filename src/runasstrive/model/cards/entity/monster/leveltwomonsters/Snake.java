package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Ice;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Bite;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Snake extends Monster {
    private static final String NAME = "Snake";
    private static final int HP = 31;
    private static final int BITE_LEVEL = 2;
    private static final int FOCUS_LEVEL = 2;
    private static final int ICE_LEVEL = 2;
    private static final List<Ability> ABILITIES = List.of(new Bite(BITE_LEVEL), new Focus(FOCUS_LEVEL), new Ice(ICE_LEVEL, ICE_LEVEL));

    public Snake() {
        super(NAME, HP, MonsterType.ICE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
