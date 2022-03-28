package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Lightning;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

/**
 * This class represents a Skeleton.
 *
 * @author ugget
 * @version 1.0
 */
public class Skeleton extends Monster {
    private static final String NAME = "Skeleton";
    private static final int HP = 14;
    private static final int FOCUS_LEVEL = 1;
    private static final int LIGHTNING_LEVEL = 1;
    private static final List<Ability> ABILITIES
            = List.of(new Focus(FOCUS_LEVEL), new Lightning(LIGHTNING_LEVEL, LIGHTNING_LEVEL));

    /**
     * This method constructs a new Skeleton.
     */
    public Skeleton() {
        super(NAME, HP, MonsterType.LIGHTNING, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
