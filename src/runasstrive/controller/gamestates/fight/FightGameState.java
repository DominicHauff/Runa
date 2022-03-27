package runasstrive.controller.gamestates.fight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.afterfight.ChooseNewCards;
import runasstrive.controller.gamestates.afterfight.ChooseReward;
import runasstrive.controller.gamestates.afterfight.Heal;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.model.FightResult;
import runasstrive.model.levels.FightLog;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;

public abstract class FightGameState extends GameState {
    private FightLog fightLog;

    protected FightGameState(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (this.interact(parameterBundle)) {
            this.setNextGameState();
            this.setResponse();
            if (this.fight()) {
                this.setGameStateAfterFight();
                this.setResponseAfterFight();
            }
            return true;
        }
        return false;
    }

    protected boolean fight() {
        if (this.nextGameState == null) {
            this.fightLog = this.runasStrive.startFight();
            return true;
        }
        return false;
    }

    public void setGameStateAfterFight() {
        switch (this.fightLog.getFightResult()) {
            case GAME_OVER:
            case GAME_WON:
                this.nextGameState = null;
                break;
            case STAGE_CLEARED:
                this.nextGameState = this.runasStrive.canChooseDie() ? ChooseReward.class : ChooseNewCards.class;
                break;
            case LEVEL_CLEARED:
                this.nextGameState = this.runasStrive.canPlayerHeal() ? Heal.class : InitializeLevel.class;
                break;
            case CONTINUE:
                this.nextGameState = ChooseAbility.class;
            default: break;
        }
    }

    protected void setResponseAfterFight() {
        final String fightLog = this.fightLog.getFightLog();
        this.response = this.response == null ? fightLog : this.response + fightLog;
        if (this.nextGameState == ChooseReward.class) {
            this.response += System.lineSeparator() + Messages.CHOOSE_RUNAS_REWARD
                    + System.lineSeparator() + Messages.CHOOSE_NEW_ABILITIES_OPTION
                    + System.lineSeparator() + Messages.CHOOSE_NEW_DIE;
            return;
        }
        if (this.fightLog.getFightResult().equals(FightResult.GAME_WON)) {
            this.response += System.lineSeparator() + Messages.GAME_WON;
            return;
        }
        if (this.fightLog.getFightResult().equals(FightResult.LEVEL_CLEARED)) {
            StringBuilder builder = new StringBuilder();
            this.runasStrive.getPlayer().getType().getUpgraded().forEach(ability ->
                    builder.append(String.format(Messages.GET_NEW_CARD, ability)).append(System.lineSeparator()));
            this.response += System.lineSeparator() + builder.toString().trim();
        }
    }


}
