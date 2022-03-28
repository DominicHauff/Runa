package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Lightning;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Scratch;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

/**
 * This class represents a Shadow Blade.
 *
 * @author ugget
 * @version 1.0
 */
public class ShadowBlade extends Monster {
    private static final String NAME = "Shadow Blade";
    private static final int HP = 27;
    private static final int SCRATCH_LEVEL = 2;
    private static final int FOCUS_LEVEL = 2;
    private static final int LIGHTNING_LEVEL = 2;
    private static final List<Ability> ABILITIES = List.of(
            new Scratch(SCRATCH_LEVEL),
            new Focus(FOCUS_LEVEL),
            new Lightning(LIGHTNING_LEVEL, LIGHTNING_LEVEL)
    );

    /**
     * This method constructs a new ShadowBlade object
     */
    public ShadowBlade() {
        super(NAME, HP, MonsterType.LIGHTNING, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
