package runasstrive.model.cards.entity.monster.leveltwomonsters;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.type.MonsterType;

import java.util.List;

public class MegaSaurus extends Monster {
    private static final String NAME = "Mega Saurus";
    private static final int HP = 100;
    private static final List<Ability> ABILITIES = List.of();//TODO: figure out how monsters get their abilities

    public MegaSaurus() {
        super(NAME, HP, MonsterType.NONE, ABILITIES);
    }
}
