package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.offensive.Lightning;
import runasstrive.model.cards.ablilities.magic.offensive.Water;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class DarkElf extends Monster {
    private static final String NAME = "Dark Elf";
    private static final int HP = 34;
    private static final int FOCUS_LEVEL = 2;
    private static final int WATER_LEVEL = 1;
    private static final int LIGHTNING_LEVEL = 1;
    private static final List<Ability> ABILITIES = List.of(
            new Focus(FOCUS_LEVEL),
            new Water(WATER_LEVEL, LIGHTNING_LEVEL),
            new Lightning(LIGHTNING_LEVEL, LIGHTNING_LEVEL)
    );

    public DarkElf() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }
}
