package runasstrive.controller.gamestates;

import runasstrive.controller.gamestates.afterfight.ChooseReward;
import runasstrive.controller.gamestates.afterfight.Heal;
import runasstrive.controller.gamestates.afterfight.UpgradeAbilities;
import runasstrive.controller.gamestates.end.GameOver;
import runasstrive.controller.gamestates.end.GameWon;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.controller.gamestates.fight.ChooseTarget;
import runasstrive.controller.gamestates.fight.RollDie;
import runasstrive.controller.gamestates.fight.UseAbility;
import runasstrive.controller.gamestates.init.ChooseCharacterClass;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.model.RunasStrive;

import java.util.HashMap;
import java.util.Map;

public class GameStateSupplier {
    private final Map<Class<? extends GameState>, GameState> gameStates;

    public GameStateSupplier(RunasStrive runasStrive) {
        this.gameStates = new HashMap<>();
        this.gameStates.put(ChooseCharacterClass.class, new ChooseCharacterClass(runasStrive));
        this.gameStates.put(InitializeLevel.class, new InitializeLevel(runasStrive));
        this.gameStates.put(ChooseAbility.class, new ChooseAbility(runasStrive));
        this.gameStates.put(ChooseTarget.class, new ChooseTarget(runasStrive));
        this.gameStates.put(RollDie.class, new RollDie(runasStrive));
        this.gameStates.put(UseAbility.class, new UseAbility(runasStrive));
        this.gameStates.put(ChooseReward.class, new ChooseReward(runasStrive));
        this.gameStates.put(UpgradeAbilities.class, new UpgradeAbilities(runasStrive));
        this.gameStates.put(Heal.class, new Heal(runasStrive));
        this.gameStates.put(GameWon.class, new GameWon(runasStrive));
        this.gameStates.put(GameOver.class, new GameOver(runasStrive));
    }

    public Map<Class<? extends GameState>, GameState> getGameStates() {
        return this.gameStates;
    }

}
