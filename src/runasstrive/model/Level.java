package runasstrive.model;

import runasstrive.model.cards.entity.monster.Monster;

public enum Level {
    //TODO: add boss monsters
    LEVEL_ONE(1, 4, new int[]{1, 2, 2}, null),
    LEVEL_TWO(2, 4, new int[]{1, 2, 2}, null);

    private final int value;
    private final int numOfStages;
    private final int[] stagesPerLevel;
    private final Monster levelBoss;

    Level(int value, int numOfStages, int[] stagesPerLevel, Monster levelBoss) {
        this.value = value;
        this.numOfStages = numOfStages;
        this.stagesPerLevel = stagesPerLevel;
        this.levelBoss = levelBoss;
    }

    public int getValue() {
        return this.value;
    }

    public int getNumOfStages() {
        return numOfStages;
    }

    public int[] getStagesPerLevel() {
        return stagesPerLevel;
    }

    public Monster getLevelBoss() {
        return levelBoss;
    }
}
