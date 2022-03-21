package runasstrive.model;

public enum Level {
    LEVEL_ONE(1)/* {
        @Override
        public Level getNextLevel() {
            return LEVEL_TWO;
        }
    }*/,
    LEVEL_TWO(2) /*{
        @Override
        public Level getNextLevel() {
            return null;
        }
    }*/;

    private final int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    //public abstract Level getNextLevel();
}
