package runasstrive.builder;

import runasstrive.model.levels.Level;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.monster.levelonemonsters.Frog;
import runasstrive.model.cards.entity.monster.levelonemonsters.Ghost;
import runasstrive.model.cards.entity.monster.levelonemonsters.Goblin;
import runasstrive.model.cards.entity.monster.levelonemonsters.Gorgon;
import runasstrive.model.cards.entity.monster.levelonemonsters.Mushroomlin;
import runasstrive.model.cards.entity.monster.levelonemonsters.Rat;
import runasstrive.model.cards.entity.monster.levelonemonsters.Skeleton;
import runasstrive.model.cards.entity.monster.levelonemonsters.Spider;
import runasstrive.model.cards.entity.monster.leveltwomonsters.Bear;
import runasstrive.model.cards.entity.monster.leveltwomonsters.DarkElf;
import runasstrive.model.cards.entity.monster.leveltwomonsters.Hornet;
import runasstrive.model.cards.entity.monster.leveltwomonsters.Mushroomlon;
import runasstrive.model.cards.entity.monster.leveltwomonsters.ShadowBlade;
import runasstrive.model.cards.entity.monster.leveltwomonsters.Snake;
import runasstrive.model.cards.entity.monster.leveltwomonsters.Tarantula;
import runasstrive.model.cards.entity.monster.leveltwomonsters.WildBoar;
import runasstrive.model.levels.GameLevel;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * This class is a utility class used to instantiate all
 * needed objects and eventually build and provide new
 * {@link GameLevel} objects to the application.
 *
 * @author ugget
 * @version 1.0
 */
public final class LevelBuilder {
    private LevelBuilder() {

    }

    /**
     * This method creates a new stack that holds
     * all {@link GameLevel} objects.
     *
     * @return the stack that holds the levels
     */
    public static Stack<GameLevel> buildLevels() {
        final Stack<GameLevel> gameLevels = new Stack<>();
        gameLevels.push(getLevelTwo());
        gameLevels.push(getLevelOne());
        return gameLevels;
    }

    private static GameLevel getLevelOne() {
        final Collection<Monster> monsters = List.of(
                new Frog(), new Ghost(), new Gorgon(), new Skeleton(),
                new Spider(), new Goblin(), new Rat(), new Mushroomlin()
        );
        return new GameLevel(Level.LEVEL_ONE, monsters);
    }

    private static GameLevel getLevelTwo() {
        final Collection<Monster> monsters = List.of(
                new Snake(), new DarkElf(), new ShadowBlade(), new Hornet(),
                new Tarantula(), new Bear(), new Mushroomlon(), new WildBoar()
        );
        return new GameLevel(Level.LEVEL_TWO, monsters);
    }

}
