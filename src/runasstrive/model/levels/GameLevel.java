package runasstrive.model.levels;

import runasstrive.model.Level;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * This class represents one level of the game
 * consisting of 4 stages with corresponding numbers
 * of monsters. The player has to clear each stage to
 * clear the level and each level to
 * win the game.
 *
 * @author ugget
 * @version 1.0
 */
public class GameLevel {
    private final Level level;
    private final Stack<Stage> stages;
    private final List<Monster> monsters;

    /**
     * This method constructs a new GameLevel object.
     *
     * @param level the level number
     * @param monsters a list of corresponding monsters for each level
     */
    public GameLevel(Level level, Collection<Monster> monsters) {
        this.level = level;
        this.stages = new Stack<>();
        this.monsters = new ArrayList<>(monsters);
    }

    /**
     * @return the current stage
     */
    public Stage getCurrentStage() {
        return this.stages.peek();
    }

    /**
     * This method initializes the level by shuffling
     * the monster cards and dealing the corresponding
     * amount of cards to each stage.
     *
     * @param seed the seed used to shuffle the monster cards
     */
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

    /**
     * This method returns a FightLog object which contains
     * all information about the latest fight.
     *
     * @param player the player
     * @return the fight log
     */
    public FightLog resume(Player player) {
        return this.getCurrentStage().enter(player);
    }

    /**
     * This method updates the current stage after
     * the previous stage is cleared.
     */
    public void enterNextStage() {
        this.stages.pop();
    }

    /**
     * @return the number of the level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return whether the level is cleared
     */
    public boolean cleared() {
        return this.stages.isEmpty();
    }
}
