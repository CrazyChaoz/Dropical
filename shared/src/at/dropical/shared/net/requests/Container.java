package at.dropical.shared.net.requests;

import at.dropical.shared.GameState;

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
