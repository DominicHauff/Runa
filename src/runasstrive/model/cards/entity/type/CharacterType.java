package runasstrive.model.cards.entity.type;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.ablilities.magic.defensive.Focus;
import runasstrive.model.cards.ablilities.magic.defensive.Reflect;
import runasstrive.model.cards.ablilities.magic.offensive.Water;
import runasstrive.model.cards.ablilities.physical.defensive.Parry;
import runasstrive.model.cards.ablilities.physical.offensive.player.Slash;
import runasstrive.model.cards.ablilities.physical.offensive.player.Thrust;

import java.util.Arrays;
import java.util.List;

public enum CharacterType {
    WARRIOR(1, List.of(new Thrust(1), new Parry(1))) {
        @Override
        public List<Ability> getUpgraded() {
            final Thrust upgradedThrust = new Thrust(this.getTypeAbilities().get(0).getLevel() + 1);
            final Parry upgradedParry = new Parry(this.getTypeAbilities().get(1).getLevel() + 1);
            return List.of(upgradedThrust, upgradedParry);
        }
    },
    MAGE(2, List.of(new Focus(1), new Water(1, 1))) {
        @Override
        public List<Ability> getUpgraded() {
            final Focus upgradedFocus = new Focus(this.getTypeAbilities().get(0).getLevel() + 1);
            final Water upgradedWater = new Water(this.getTypeAbilities().get(1).getLevel() + 1, 1); //TODO: cost
            return List.of(upgradedFocus, upgradedWater);
        }
    },
    PALADIN(3, List.of(new Slash(1), new Reflect(1))) {
        @Override
        public List<Ability> getUpgraded() {
            final Slash slash = new Slash(this.getTypeAbilities().get(0).getLevel() + 1);
            final Water water = new Water(this.getTypeAbilities().get(1).getLevel() + 1, 0); //TODO: cost
            return List.of(slash, water);
        }
    };

    final int value;
    final List<Ability> typeAbilities;

    CharacterType(int value, List<Ability> typeAbilities) {
        this.value = value;
        this.typeAbilities = typeAbilities;
    }

    public static CharacterType getCharacterType(int value) {
        return Arrays.stream(CharacterType.values()).filter(type -> type.value == value).findFirst().orElse(null);
    }

    public List<Ability> getTypeAbilities() {
        return typeAbilities;
    }

    public abstract List<Ability> getUpgraded();
}
