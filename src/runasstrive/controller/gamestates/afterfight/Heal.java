package runasstrive.controller.gamestates.afterfight;

import runasstrive.controller.gamestates.GameState;
import runasstrive.controller.gamestates.fight.ChooseAbility;
import runasstrive.controller.gamestates.init.InitializeLevel;
import runasstrive.io.parameters.MultipleChoiceParameter;
import runasstrive.io.parameters.Parameter;
import runasstrive.io.parameters.ParameterBundle;
import runasstrive.io.resources.Messages;
import runasstrive.model.RunasStrive;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Heal extends GameState {
    private static final MultipleChoiceParameter CHOICES = new MultipleChoiceParameter();
    private static final int CARD_INDEX_OFFSET = 1;
    private static final List<Parameter<?>> PARAMETERS = List.of(CHOICES);

    public Heal(RunasStrive runasStrive) {
        super(runasStrive);
    }

    @Override
    public String getPrompt() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(Messages.HEALING_OPTION, this.runasStrive.getPlayer().getHp())).append(System.lineSeparator());
        IntStream.range(0, this.runasStrive.getPlayer().getAbilities().size()).forEach(i -> {
            builder.append(String.format(Messages.LIST_ELEMENT, i + CARD_INDEX_OFFSET,
                    this.runasStrive.getPlayer().getAbilities().get(i).toString())).append(System.lineSeparator());
        });
        return builder + this.repeatPrompt();
    }

    @Override
    public String repeatPrompt() {
        return this.runasStrive.getPlayer().getAbilities().size() > 2
                ? String.format(Messages.MULTIPLE_CARDS_PROMPT, this.runasStrive.getPlayer().getAbilities().size())
                : String.format(Messages.ENTER_NUMBER_PROMPT, this.runasStrive.getPlayer().getAbilities().size());
    }

    @Override
    public boolean execute(ParameterBundle parameterBundle) {
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

        this.nextGameState = this.runasStrive.getCurrentLevel().cleared()
                ? InitializeLevel.class : ChooseAbility.class;
        final String enterStage = this.nextGameState == ChooseAbility.class
                ? String.format(Messages.STAGE_ENTER_MESSAGE,
                this.runasStrive.getCurrentLevel().getCurrentStage().getStageNumber(),
                this.runasStrive.getCurrentLevel().getLevel().getValue()) : null;

        if (choices.isEmpty()) {
            this.nextGameState = this.runasStrive.getCurrentLevel().cleared()
                    ? InitializeLevel.class : ChooseAbility.class;
            this.response = this.nextGameState == ChooseAbility.class ? enterStage : null;
            return true;
        }

        this.runasStrive
                .healPlayer(choices.stream().map(choice -> choice - CARD_INDEX_OFFSET).collect(Collectors.toList()));

        this.response = String.format(Messages.GAIN_HEALTH, this.runasStrive.getPlayerGainedHp())
                + System.lineSeparator();
        if (enterStage != null) {
            this.response += System.lineSeparator() + enterStage;
        }
        return true;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return PARAMETERS;
    }
}
