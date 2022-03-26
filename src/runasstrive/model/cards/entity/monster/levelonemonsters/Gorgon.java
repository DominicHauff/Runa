package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Fire;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class Gorgon extends Monster {
    private static final String NAME = "Gorgon";
    private static final int HP = 13;
    private static final int FOCUS_LEVEL = 1;
    private static final int FIRE_LEVEL = 1;

    private static final List<Ability> ABILITIES = List.of(new Focus(FOCUS_LEVEL), new Fire(FIRE_LEVEL, FIRE_LEVEL));

    public Gorgon() {
        super(NAME, HP, MonsterType.FIRE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
