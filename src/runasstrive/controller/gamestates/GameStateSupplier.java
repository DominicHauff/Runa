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

public class GameStateSupplier {
    private final Map<Class<? extends GameState>, GameState> gameStates;

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

    public GameState get(Class<? extends GameState> next) {
        return this.gameStates.get(next);
    }
}
