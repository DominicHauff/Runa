package runasstrive.controller.gamestates.fight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.afterfight.ChooseNewCards;
import runasstrive.controller.gamestates.afterfight.ChooseReward;
import runasstrive.controller.gamestates.afterfight.Heal;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;

public abstract class FightGameState extends GameState {

    protected FightGameState(RunasStrive runasStrive) {
        super(runasStrive);
    }

    public void startFight() {
        this.runasStrive.startFight();
        final String fightLog = this.runasStrive.getFightLog();
        this.response = this.response == null ? fightLog : this.response + fightLog;
        if (this.runasStrive.gameOver()) {
            this.nextGameState = null;
            return;
        }

        if (!this.runasStrive.stageCleared()) {
            this.nextGameState = ChooseAbility.class;
            return;
        }
        this.runasStrive.advanceToNextStage();
        if (!this.runasStrive.isLevelCleared()) {
            if (this.runasStrive.canChooseDie()) {
                this.response += System.lineSeparator() + Messages.CHOOSE_RUNAS_REWARD
                        + System.lineSeparator() + Messages.CHOOSE_NEW_ABILITIES_OPTION
                        + System.lineSeparator() + Messages.CHOOSE_NEW_DIE;
                this.nextGameState = ChooseReward.class;
                return;
            }
            this.nextGameState = ChooseNewCards.class;
            return;
        }

        if (this.runasStrive.gameWon()) {
            this.response += System.lineSeparator() + Messages.GAME_WON;
            this.nextGameState = null;
            return;
        }

        this.runasStrive.advanceToNextLevel();
        this.runasStrive.upgradeCards();


        StringBuilder builder = new StringBuilder();
        this.runasStrive.getPlayer().getType().getUpgraded().forEach(ability ->
                builder.append(String.format(Messages.GET_NEW_CARD, ability)).append(System.lineSeparator()));
        this.response += System.lineSeparator() + builder.toString().trim();

        this.nextGameState = this.runasStrive.canPlayerHeal() ? Heal.class : InitializeLevel.class;
    }

    public void setGameStateAfterFight() {
        if (this.runasStrive.gameOver()) {
            this.nextGameState = null;
            return;
        }
        if (!this.runasStrive.stageCleared()) {
            this.nextGameState = ChooseAbility.class;
            return;
        }
    }

    @Override
    protected boolean abstractExecute(ParameterBundle parameterBundle) {
        /*
         * interaction before fight
         * set game state if not fight
         * set response according to game state
         * if fight
         * set game state after fight
         * set response after fight
         * */
        boolean lorenz = true;
        return lorenz;
    }

    protected abstract void interactAfterFight();

}
