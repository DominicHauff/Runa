package runasstrive.controller.gamestates.init;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.io.parameters.MultipleChoiceParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.levels.GameLevel;

import java.util.List;

public class InitializeLevel extends GameState {
    private static final MultipleChoiceParameter SEEDS = new MultipleChoiceParameter();
    private static final int FIRST_SEED_INDEX = 0;
    private static final int SECOND_SEED_INDEX = 1;
    private static final int EXPECTED_NUM_SEEDS = 2;
    private static final List<Parameter<?>> PARAMETERS = List.of(SEEDS);

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
        return Messages.ENTER_SEEDS_PROMPT + System.lineSeparator();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        List<Integer> seeds = parameterBundle.get(SEEDS);
        if (seeds.size() != EXPECTED_NUM_SEEDS) return false;


        final GameLevel level = this.runasStrive.shuffleCards(seeds.get(FIRST_SEED_INDEX),
                seeds.get(SECOND_SEED_INDEX));

        this.response = String.format(Messages.STAGE_ENTER_MESSAGE,
                level.getCurrentStage().getStageNumber(), level.getLevel().getValue()) + System.lineSeparator();
        this.nextGameState = ChooseAbility.class;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }

}
