package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.io.parameters.CardIndexParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.model.RunasStrive;

import java.util.List;

public class ChooseNewCards extends GameState {
    private static final CardIndexParameter FIRST = new CardIndexParameter();
    private static final CardIndexParameter SECOND = new CardIndexParameter();
    private static final List<Parameter<?>> PARAMETERS = List.of(FIRST, SECOND);

    public ChooseNewCards(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return null;
    }

    @Override
    public String repeatPrompt() {
        return null;
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
        if (!parameterBundle.isPresent(FIRST)) {
            return false;
        }
        final int firstCardIndex = parameterBundle.get(FIRST);
        if (parameterBundle.isPresent(SECOND)) {
            if (PARAMETERS.size() != this.runasStrive.getNumRewardCards()) {
                return false;
            }
            final int secondCardIndex = parameterBundle.get(SECOND);
            if (secondCardIndex == firstCardIndex) {
                return false;
            }
            if (this.runasStrive.getRewards().size() <= secondCardIndex) {
                return false;
            }
            this.runasStrive.drawCard(secondCardIndex);
        }
        this.runasStrive.drawCard(firstCardIndex);
        if (this.runasStrive.getRewards().size() <= firstCardIndex) {
            return false;
        }
        this.runasStrive.discardLeftOverReward();
        this.response = null; //TODO: set response
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
