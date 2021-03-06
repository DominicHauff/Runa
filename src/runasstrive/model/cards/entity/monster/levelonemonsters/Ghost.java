package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Ice;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

/**
 * This class represents a Ghost.
 *
 * @author ugget
 * @version 1.0
 */
public class Ghost extends Monster {
    private static final String NAME = "Ghost";
    private static final int HP = 15;
    private static final int ICE_LEVEL = 1;
    private static final int FOCUS_LEVEL = 1;

    private static final List<Ability> ABILITIES = List.of(new Focus(FOCUS_LEVEL), new Ice(ICE_LEVEL, ICE_LEVEL));

    /**
     * This method constructs a new Ghost object.
     */
    public Ghost() {
        super(NAME, HP, MonsterType.ICE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
