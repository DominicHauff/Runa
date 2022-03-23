package runasstrive.model.cards.entity.type;

import java.util.Arrays;

public enum CharacterType {
    WARRIOR(1), MAGE(2), PALADIN(3);

    final int value;

    CharacterType(int value) {
        this.value = value;
    }

    public static CharacterType getCharacterType(int value) {
        return Arrays.stream(CharacterType.values()).filter(type -> type.value == value).findFirst().orElse(null);
    }
}
