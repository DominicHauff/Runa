package runasstrive.controller.gameStates;

import runasstrive.controller.gameStates.afterfight.ChooseReward;
import runasstrive.controller.gameStates.afterfight.Heal;
import runasstrive.controller.gameStates.afterfight.UpgradeAbilities;
import runasstrive.controller.gameStates.fight.ChooseAbility;
import runasstrive.controller.gameStates.fight.ChooseTarget;
import runasstrive.controller.gameStates.fight.RollDie;
import runasstrive.controller.gameStates.init.ChooseCharacterClass;
import runasstrive.controller.gameStates.init.InitializeLevel;
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
            UpgradeAbilities.class, new UpgradeAbilities(runasStrive),
            Heal.class, new Heal(runasStrive)
        );
    }

    public Map<Class<? extends GameState>, GameState> getGameStates() {
        return this.gameStates;
    }

}
