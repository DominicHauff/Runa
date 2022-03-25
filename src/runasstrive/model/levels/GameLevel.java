package runasstrive.model.levels;

import runasstrive.model.Level;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;

import java.util.*;

public class GameLevel {
    private final Level level;
    private final Stack<Stage> stages;
    private final List<Monster> monsters;
    private Stage currentStage;

    public GameLevel(Level level, List<Monster> monsters) {
        this.level = level;
        this.stages = new Stack<>();
        this.monsters = new ArrayList<>(monsters);
    }

    public Stage getCurrentStage() {
        return this.currentStage;
    }

    public void initialize(int seed) {
        Collections.shuffle(this.monsters, new Random(seed));
        this.stages.push(new Stage(this.level.getNumOfStages(), List.of(level.getLevelBoss())));
        int index = this.monsters.size() - 1;
        for (int i = this.level.getStagesPerLevel().length - 1; i >= 0; i--) {
            List<Monster> stageMonsters = new ArrayList<>();
            for (int j = 0; j < this.level.getStagesPerLevel()[i]; j++) {
                stageMonsters.add(this.monsters.get(index));
                index--;
            }
            this.stages.push(new Stage(i + 1, stageMonsters));
        }
        this.currentStage = this.stages.pop();
    }

    public void resume(Player player) {
        if (this.currentStage.cleared()) this.currentStage = this.stages.pop();
        this.currentStage.enter(player);
    }

    public Level getLevel() {
        return level;
    }

    public boolean cleared() {
        return this.stages.isEmpty() && this.currentStage.cleared();
    }
}
