package runasstrive.controller.gamestates;

import runasstrive.controller.gamestates.afterfight.ChooseNewCards;
import runasstrive.controller.gamestates.afterfight.ChooseReward;
import runasstrive.controller.gamestates.afterfight.Heal;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.controller.gamestates.fight.ChooseTarget;
import runasstrive.controller.gamestates.fight.RollDie;
import runasstrive.controller.gamestates.init.ChooseCharacterClass;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.model.RunasStrive;

import java.util.Map;

/**
 * This class is a utility class used to instantiate
 * and provide all needed {@link GameState} objects.
 *
 * @author ugget
 * @version 1.0
 */
public class GameStateSupplier {
    private final Map<Class<? extends GameState>, GameState> gameStates;

    /**
     * This method constructs a new GameStateSupplier object.
     * @param runasStrive the instance of the {@link RunasStrive} object used by all
     *                    game states
     */
    public GameStateSupplier(RunasStrive runasStrive) {
        this.gameStates = Map.of(
                ChooseCharacterClass.class, new ChooseCharacterClass(runasStrive),
                InitializeLevel.class, new InitializeLevel(runasStrive),
                ChooseAbility.class, new ChooseAbility(runasStrive),
                ChooseTarget.class, new ChooseTarget(runasStrive),
                RollDie.class, new RollDie(runasStrive),
                ChooseReward.class, new ChooseReward(runasStrive),
                ChooseNewCards.class, new ChooseNewCards(runasStrive),
                Heal.class, new Heal(runasStrive)
        );
    }

    /**
     * This method is used to receive the {@link GameState} object
     * corresponding to a Class reference held by one game state to
     * point to the next one.
     *
     * @param next the Class reference to the next game state
     * @return the GameState object
     */
    public GameState get(Class<? extends GameState> next) {
        return this.gameStates.get(next);
    }
}
