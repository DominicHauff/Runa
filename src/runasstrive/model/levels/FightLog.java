package runasstrive.model.levels;

import runasstrive.model.FightResult;

import java.util.List;

public class FightLog {
    private final Stage stage;
    private FightResult fightResult;
    private final List<String> log;

    public FightLog(Stage stage, List<String> log) {
        this.stage = stage;
        this.log = log;
    }

    public FightResult getFightResult() {
        return fightResult;
    }

    public String getFightLog() {
        final StringBuilder builder = new StringBuilder();
        this.log.forEach(logEntry -> builder.append(logEntry).append(System.lineSeparator()));
        return builder.toString().trim();
    }

    public Stage getStage() {
        return stage;
    }

    public void setFightResult(FightResult fightResult) {
        this.fightResult = fightResult;
    }
}
