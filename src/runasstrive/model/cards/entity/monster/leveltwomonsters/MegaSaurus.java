package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Fire;
import runasstrive.model.cards.ablilities.magic.offensive.Lightning;
import runasstrive.model.cards.ablilities.physical.defensive.Block;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Bite;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

/**
 * This class represents the second level boss monster Mega Saurus.
 *
 * @author ugget
 * @version 1.0
 */
public class MegaSaurus extends Monster {
    private static final String NAME = "Mega Saurus";
    private static final int HP = 100;
    private static final int BITE_LEVEL = 2;
    private static final int BLOCK_LEVEL = 2;
    private static final int FOCUS_LEVEL = 2;
    private static final int FIRE_LEVEL = 1;
    private static final int LIGHTNING_LEVEL = 1;
    private static final List<Ability> ABILITIES = List.of(
            new Bite(BITE_LEVEL),
            new Block(BLOCK_LEVEL),
            new Focus(FOCUS_LEVEL),
            new Fire(FIRE_LEVEL, FIRE_LEVEL),
            new Lightning(LIGHTNING_LEVEL, LIGHTNING_LEVEL)
    );

    /**
     * constructs a new MegaSaurus object
     */
    public MegaSaurus() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        return FOCUS_LEVEL;
    }
}
