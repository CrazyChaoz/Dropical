package at.dropical.shared.net.abstracts;

import at.dropical.shared.GameState;

public abstract class Container implements SendableItem {
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
