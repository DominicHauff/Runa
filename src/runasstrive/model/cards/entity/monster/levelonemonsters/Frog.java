package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Water;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

/**
 * This class represents a Frog.
 *
 * @author ugget
 * @version 1.0
 */
public class Frog extends Monster {
    private static final String NAME = "Frog";
    private static final int HP = 16;
    private static final int WATER_LEVEL = 1;
    private static final int FOCUS_LEVEL = 1;

    private static final List<Ability> ABILITIES = List.of(new Focus(FOCUS_LEVEL), new Water(WATER_LEVEL, WATER_LEVEL));

    /**
     * This method constructs a new Frog object.
     */
    public Frog() {
        super(NAME, HP, MonsterType.WATER, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
