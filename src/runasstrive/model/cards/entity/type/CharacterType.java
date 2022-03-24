package runasstrive.model.cards.entity.type;

import runasstrive.model.cards.ablilities.Ability;

import java.util.Arrays;
import java.util.List;

public enum CharacterType {
    WARRIOR(1, List.of()), MAGE(2, List.of()), PALADIN(3, List.of());

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
}
