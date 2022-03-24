package runasstrive.model.levels;

import runasstrive.model.cards.entity.monster.Monster;

import java.util.Collection;
import java.util.stream.Collectors;


public class Stage {
    private final int stageNumber;
    private final Collection<Monster> monsters;

    public Stage(int stageNumber, Collection<Monster> monsters) {
        this.stageNumber = stageNumber;
        this.monsters = monsters;
    }

    public boolean cleared() {
        return this.monsters.isEmpty();
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public Collection<Monster> getMonsters() {
        return this.monsters;
    }

    public Collection<Monster> getAliveMonsters() {
        return monsters.stream()
                .filter(monster -> monster.getHp() > 0)
                .collect(Collectors.toList());
    }

    public String getFightLog() {
        //TODO: implement
        return null;
    }
}
