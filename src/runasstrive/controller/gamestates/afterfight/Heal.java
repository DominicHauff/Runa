package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.view.parameters.MultipleChoiceParameter;
import runasstrive.view.parameters.Parameter;
import runasstrive.view.parameters.ParameterBundle;
import runasstrive.view.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents the {@link GameState} in which
 * the player has won a fight and can discard ability
 * cards to gain hp.
 *
 * @author ugget
 * @version 1.0
 */
public class Heal extends GameState {
    private static final MultipleChoiceParameter CHOICES = new MultipleChoiceParameter();
    private static final int CARD_INDEX_OFFSET = 1;
    private List<Integer> choices;

    /**
     * This method constructs a new Heal object.
     *
     * @param runasStrive the instance of the {@link RunasStrive} object used by all
     *                    game states
     */
    public Heal(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        return String.format(Messages.HEALING_OPTION, this.runasStrive.getPlayer().getHp()) + System.lineSeparator()
                + this.list(this.runasStrive.getPlayer().getAbilities(), CARD_INDEX_OFFSET) + System.lineSeparator()
                + this.repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return this.runasStrive.getPlayer().getAbilities().size() > 2
                ? String.format(Messages.MULTIPLE_CARDS_PROMPT, this.runasStrive.getPlayer().getAbilities().size())
                : String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPlayer().getAbilities().size());
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
        final List<Integer> choices = parameterBundle.get(CHOICES);
        final Set<Integer> choiceSet = new HashSet<>(choices);

        if (choices.size() != choiceSet.size()) {
            return false;
        }
        for (Integer choice : choices) {
            if (choice > this.runasStrive.getPlayer().getAbilities().size()) return false;
        }
        if (choices.stream().anyMatch(choice -> this.runasStrive.getPlayer().getAbilities().size() < choice)) {
            return false;
        }
        if (choices.size() >= this.runasStrive.getPlayer().getAbilities().size()) return false;
        this.choices = choices;
        if (!choices.isEmpty()) {
            this.runasStrive
                    .healPlayer(choices.stream().map(choice -> choice - CARD_INDEX_OFFSET).collect(Collectors.toList()));
        }
        return true;
    }

    /**
     * This method sets the next game state based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setNextGameState() {
        this.nextGameState = this.runasStrive.getCurrentLevel().cleared()
                ? InitializeLevel.class : ChooseAbility.class;
    }

    /**
     * This method sets the response based on the previous interaction with {@link RunasStrive}.
     */
    @Override
    protected void setResponse() {
        final String enterStage = this.nextGameState == ChooseAbility.class
                ? String.format(Messages.STAGE_ENTER_MESSAGE,
                this.runasStrive.getCurrentLevel().getCurrentStage().getStageNumber(),
                this.runasStrive.getCurrentLevel().getLevel().getValue()) : null;
        if (choices.isEmpty()) {
            this.response = this.nextGameState == ChooseAbility.class ? enterStage : null;
            return;
        }
        this.response = String.format(Messages.GAIN_HEALTH, this.runasStrive.getPlayerGainedHp());
        if (enterStage != null) {
            this.response += System.lineSeparator() + enterStage;
        }
    }


    @Override
    public Parameter<?> getParameter() {
        return CHOICES;
    }
}
