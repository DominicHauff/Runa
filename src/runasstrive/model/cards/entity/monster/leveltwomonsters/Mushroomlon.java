package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Deflect;
import runasstrive.model.cards.ablilities.physical.defensive.Block;
import runasstrive.model.cards.ablilities.physical.offensive.monster.Scratch;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

/**
 * This class represents a Mushroomlon.
 *
 * @author ugget
 * @version 1.0
 */
public class Mushroomlon extends Monster {
    private static final String NAME = "Mushroomlon";
    private static final int HP = 50;
    private static final int DEFLECT_LEVEL = 2;
    private static final int SCRATCH_LEVEL = 2;
    private static final int BLOCK_LEVEL = 2;
    private static final List<Ability> ABILITIES = List.of(
            new Deflect(DEFLECT_LEVEL),
            new Scratch(SCRATCH_LEVEL),
            new Block(BLOCK_LEVEL)
    );

    /**
     * constructs a new Mushroomlon object
     */
    public Mushroomlon() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }

    @Override
    public int getFocusLevel() {
        //TODO: hi
        return 0;
    }
}
