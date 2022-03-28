package runasstrive.model.levels;

import runasstrive.model.FightResult;

import java.util.List;

/**
 * This class describes a fight-log.
 * A fight-log object is used to hold all required information about a fight interaction.
 *
 * @author ugget
 * @version 1.0
 */
public class FightLog {
    private final Stage stage;
    private FightResult fightResult;
    private final List<String> log;

    /**
     * Constructs a new FightLog instance
     *
     * @param stage the {@link Stage} in which the fight took place
     * @param log a list of all logged interactions
     */
    public FightLog(Stage stage, List<String> log) {
        this.stage = stage;
        this.log = log;
    }

    /**
     * Getter for the fightResult attribute
     * @return the {@link FightResult}
     */
    public FightResult getFightResult() {
        return fightResult;
    }

    /**
     * Setter for the fightResult attribute
     *
     * @param fightResult sets the {@link FightResult} for this fight.
     */
    public void setFightResult(FightResult fightResult) {
        this.fightResult = fightResult;
    }

    /**
     * Gets the log converted into a string
     *
     * @return the log of the fight represented as a string
     */
    public String getFightLog() {
        final StringBuilder builder = new StringBuilder();
        this.log.forEach(logEntry -> builder.append(logEntry).append(System.lineSeparator()));
        return builder.toString().trim();
    }

    /**
     * Getter for the stage attribute
     * @return the {@link Stage} in which this fight took place
     */
    public Stage getStage() {
        return stage;
    }
}
