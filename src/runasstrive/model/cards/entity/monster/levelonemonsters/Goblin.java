package runasstrive.model.cards.entity.monster.levelonemonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Deflect;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Smash;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

/**
 * This class represents a Goblin.
 *
 * @author ugget
 * @version 1.0
 */
public class Goblin extends Monster {
    private static final String NAME = "Goblin";
    private static final int HP = 12;
    private static final int SMASH_LEVEL = 1;
    private static final int DEFLECT_LEVEL = 1;
    private static final List<Ability> ABILITIES = List.of(new Smash(SMASH_LEVEL), new Deflect(DEFLECT_LEVEL));

    /**
     * This method constructs a new Goblin object.
     */
    public Goblin() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        //TODO: what
        return 0;
    }
}
