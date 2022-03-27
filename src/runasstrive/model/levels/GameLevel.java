package runasstrive.model.levels;

import runasstrive.model.Level;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;

import java.util.*;

public class GameLevel {
    private final Level level;
    private final Stack<Stage> stages;
    private final List<Monster> monsters;

    public GameLevel(Level level, Collection<Monster> monsters) {
        this.level = level;
        this.stages = new Stack<>();
        this.monsters = new ArrayList<>(monsters);
    }

    public Stage getCurrentStage() {
        return this.stages.peek();
    }

    public void initialize(int seed) {
        Collections.shuffle(this.monsters, new Random(seed));
        this.stages.push(new Stage(this.level.getNumOfStages(), List.of(level.getLevelBoss())));
        int index = Arrays.stream(this.level.getMonstersPerStage()).sum() - 1;
        for (int i = this.level.getMonstersPerStage().length - 1; i >= 0; i--) {
            LinkedList<Monster> stageMonsters = new LinkedList<>();
            for (int j = 0; j < this.level.getMonstersPerStage()[i]; j++) {
                stageMonsters.addFirst(this.monsters.get(index));
                index--;
            }
            this.stages.push(new Stage(i + 1, stageMonsters));
        }
    }

    public FightLog resume(Player player) {
        return this.getCurrentStage().enter(player);
    }

    public void enterNextStage() {
        this.stages.pop();
    }

    public Level getLevel() {
        return level;
    }

    public boolean cleared() {
        return this.stages.isEmpty();
    }
}
