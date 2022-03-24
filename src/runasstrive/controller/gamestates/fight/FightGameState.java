package runasstrive.controller.gamestates.fight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.afterfight.ChooseNewCards;
import runasstrive.controller.gamestates.afterfight.ChooseReward;
import runasstrive.controller.gamestates.afterfight.Heal;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

public abstract class FightGameState extends GameState {

    protected FightGameState(RunasStrive runasStrive) {
        super(runasStrive);
    }

    public void startFight() {
        this.runasStrive.startFight();
        this.response = this.runasStrive.getFightLog();
        if (this.runasStrive.gameOver()) {
            this.nextGameState = null;
            return;
        }

        if (this.runasStrive.gameWon()) {
            this.response += System.lineSeparator() + Messages.GAME_WON + System.lineSeparator();
            this.nextGameState = null;
            return;
        }

        if (!this.runasStrive.stageCleared()) {
            this.nextGameState = ChooseAbility.class;
            return;
        }

        if (!this.runasStrive.isLevelCleared()) {
            if (this.runasStrive.canChooseDie()) {
                this.nextGameState = ChooseReward.class;
                return;
            }
            this.nextGameState = ChooseNewCards.class;
            return;
        }
        this.runasStrive.advanceToNextLevel();
        if (this.runasStrive.canPlayerHeal()) {
            this.nextGameState = Heal.class;
            return;
        }

        this.runasStrive.upgradeCards();
        StringBuilder builder = new StringBuilder();
        this.runasStrive.getPlayer().getType().getTypeAbilities().forEach(ability ->
                builder.append(ability.toString()).append(System.lineSeparator()));
        this.response += System.lineSeparator() + builder.toString();
        this.nextGameState = ChooseAbility.class;
    }

}