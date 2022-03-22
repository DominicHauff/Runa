package runasstrive.model.levels;

import java.util.Stack;

public class GameLevel {
    private final Stack<Stage> stages;

    public GameLevel(Stack<Stage> stages) {
        this.stages = stages;
    }

    public Stage getCurrentStage() {
        //TODO: fix null pointer exception
        if (this.stages.peek().cleared()) this.stages.pop();
        return this.stages.peek();
    }
}
