package at.dropical.shared.net.abstracts;

import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Request;

public abstract class Container implements Request {
    private GameState currentState;

    public Container(GameState currentState) {
        this.currentState = currentState;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }
}
