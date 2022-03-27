package runasstrive.controller.gamestates.init;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.model.Level;
import runasstrive.view.parameters.MultipleChoiceParameter;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.levels.GameLevel;

import java.util.List;

public class InitializeLevel extends GameState {
    private static final MultipleChoiceParameter SEEDS = new MultipleChoiceParameter();
    private static final int FIRST_SEED_INDEX = 0;
    private static final int SECOND_SEED_INDEX = 1;
    private static final int EXPECTED_NUM_SEEDS = 2;

    public InitializeLevel(RunasStrive runasStrive) {
        super(runasStrive);
        this.response = null;
    }

    @Override
    public String getPrompt() {
        return Messages.ENTER_SEED_DIALOG + System.lineSeparator() + repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return Messages.ENTER_SEEDS_PROMPT;
    }

    @Override
    public Parameter<?> getParameter() {
        return SEEDS;
    }

    /**
     * This method performs the interaction with {@link RunasStrive} using the given input
     * parameters.
     *
     * @param parameterBundle holds all required parameters for the game state
     * @return a boolean based on whether the interaction with the given input was performed
     * successfully
     */
    @Override
    protected boolean interact(ParameterBundle parameterBundle) {
        List<Integer> seeds = parameterBundle.get(SEEDS);
        if (seeds.size() != EXPECTED_NUM_SEEDS) {
            return false;
        }
        this.runasStrive.shuffleCards(seeds.get(FIRST_SEED_INDEX), seeds.get(SECOND_SEED_INDEX));
        return true;
    }

    /**
     * This method sets the next game state based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setNextGameState() {
        this.nextGameState = ChooseAbility.class;
    }

    /**
     * This method sets the response based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setResponse() {
        final GameLevel level = this.runasStrive.getCurrentLevel();
        this.response = String.format(Messages.STAGE_ENTER_MESSAGE,
                level.getCurrentStage().getStageNumber(), level.getLevel().getValue());
    }

}
