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

/**
 * This enum represents the different playable character types,
 * one of which gets chosen by and assigned to the player at the
 * start of the game.
 *
 * @author ugget
 * @version 1.0
 */
public enum CharacterType {

    /**
     * the Warrior type, which has {@link Thrust} and {@link Parry} by default
     */
    WARRIOR(1, "Warrior", List.of(new Thrust(1), new Parry(1))) {
        @Override
        public List<Ability> getUpgraded() {
            final Thrust upgradedThrust = new Thrust(this.getTypeAbilities().get(0).getLevel() + 1);
            final Parry upgradedParry = new Parry(this.getTypeAbilities().get(1).getLevel() + 1);
            return List.of(upgradedThrust, upgradedParry);
        }
    },

    /**
     * the Mage type, which has {@link Focus} and {@link Water} by default
     */
    MAGE(2, "Mage", List.of(new Focus(1), new Water(1, 1))) {
        @Override
        public List<Ability> getUpgraded() {
            final Focus upgradedFocus = new Focus(this.getTypeAbilities().get(0).getLevel() + 1);
            final Water upgradedWater = new Water(this.getTypeAbilities().get(1).getLevel() + 1, 1); //TODO: cost
            return List.of(upgradedFocus, upgradedWater);
        }
    },

    /**
     * the Paladin type, which has {@link Slash} and {@link Reflect} by default
     */
    PALADIN(3, "Paladin", List.of(new Slash(1), new Reflect(1))) {
        @Override
        public List<Ability> getUpgraded() {
            final Slash slash = new Slash(this.getTypeAbilities().get(0).getLevel() + 1);
            final Reflect reflect = new Reflect(this.getTypeAbilities().get(1).getLevel() + 1);
            return List.of(slash, reflect);
        }
    };

    private final int value;
    private final String name;
    private final List<Ability> typeAbilities;

    /**
     * constructs a new CharacterType
     *
     * @param value represents type's the list index
     * @param name the type's name
     * @param typeAbilities the type's default abilities
     */
    CharacterType(int value, String name, List<Ability> typeAbilities) {
        this.value = value;
        this.name = name;
        this.typeAbilities = typeAbilities;
    }

    /**
     * @param value a list index used to receive a specific CharacterType
     * @return returns the CharacterType with the matching list index
     */
    public static CharacterType getCharacterType(int value) {
        return Arrays.stream(CharacterType.values()).filter(type -> type.value == value).findFirst().orElse(null);
    }

    /**
     * @return returns the type's default abilities
     */
    public List<Ability> getTypeAbilities() {
        return this.typeAbilities;
    }

    /**
     * @return returns a list of the upgraded versions of the characters default abilities
     */
    public abstract List<Ability> getUpgraded();

    /**
     * @return returns the type's list index
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return returns the type's name
     */
    public String getName() {
        return this.name;
    }
}
