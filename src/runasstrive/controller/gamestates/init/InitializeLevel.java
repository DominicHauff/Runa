package runasstrive.controller.gamestates.init;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.parameters.SeedParameter;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;
import runasstrive.model.levels.GameLevel;

import java.util.List;

public class InitializeLevel extends GameState {
    private static final SeedParameter FIRST_SEED = new SeedParameter();
    private static final SeedParameter SECOND_SEED = new SeedParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(FIRST_SEED, SECOND_SEED);

    public InitializeLevel(RunasStrive runasStrive) {
        super(runasStrive);
        this.response = null;
    }

    @Override
    public String getPrompt() {
        return Messages.ENTER_SEEDS_PROMPT;
    }

    @Override
    public String repeatPrompt() {
        return getPrompt();
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (!parameterBundle.isPresent(FIRST_SEED) || !parameterBundle.isPresent(SECOND_SEED)) {
            return false;
        }
        final int firstSeed = parameterBundle.get(FIRST_SEED);
        final int secondSeed = parameterBundle.get(SECOND_SEED);
        final GameLevel level = this.runasStrive.shuffleCards(firstSeed, secondSeed);
        this.response = String.format(Messages.STAGE_ENTER_MESSAGE,
                level.getCurrentStage().getStageNumber(), level.getLevel().getValue());
        this.nextGameState = ChooseAbility.class;
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }

}
