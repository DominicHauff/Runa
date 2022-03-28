package runasstrive.builder;

import runasstrive.model.cards.ablilities.Ability;
import runasstrive.model.cards.entity.player.Player;

import java.util.Collection;
import java.util.Collections;

/**
 * This class is a utility class used to instantiate
 * a new {@link Player} object with all initial values
 * except for the character type which is determined at
 * runtime.
 *
 * @author ugget
 * @version 1.0
 */
public final class PlayerBuilder {
    private static final String PLAYER_NAME = "Runa";
    private static final int HEAL = 10;
    private static final int MAX_HP = 50;
    private static final int MAX_FP = 4;
    private static final int DEFAULT_FP = 1;
    private static final int MIN_ABILITY_CARDS = 1;
    private static final Collection<Ability> DEFAULT_ABILITIES = Collections.emptyList();

    private PlayerBuilder() {

    }

    /**
     * This method instantiates a new player.
     *
     * @return the player object
     */
    public static Player buildPlayer() {
        return new Player(PLAYER_NAME, HEAL, MIN_ABILITY_CARDS, MAX_HP, DEFAULT_FP, MAX_FP, DEFAULT_ABILITIES);
    }
}
