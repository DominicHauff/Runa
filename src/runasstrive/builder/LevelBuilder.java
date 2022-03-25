package runasstrive.builder;

import runasstrive.model.Level;
import runasstrive.model.cards.entity.monster.Monster;
import runasstrive.model.cards.entity.monster.levelonemonsters.*;
import runasstrive.model.cards.entity.monster.leveltwomonsters.*;
import runasstrive.model.levels.GameLevel;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class LevelBuilder {

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
