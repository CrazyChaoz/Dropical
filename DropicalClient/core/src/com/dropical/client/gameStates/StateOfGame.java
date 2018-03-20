package com.dropical.client.gameStates;

import com.dropical.client.serverEssentials.PollRequest;
import com.dropical.client.server.TetrisPlayer;

public class StateOfGame {
    private GameStatePattern gameState;
    private GameStatePattern stateLost;
    private GameStatePattern statePaused;
    private GameStatePattern stateRunning;
    private GameStatePattern stateStarting;
    private GameStatePattern stateStopped;
    private GameStatePattern stateWon;

    public StateOfGame() {
        this.gameState = new Starting(this);

        stateLost = new Lost(this);
        statePaused = new Paused(this);
        stateRunning = new Running(this);
        stateStarting = new Starting(this);
        stateStopped = new Stopped(this);
        stateWon = new Won(this);
    }

    //----------------------------------------------------------

    public PollRequest buildPollRequest(PollRequest poll) {
        return gameState.buildPollRequest(poll);
    }
    public void setPlayers(TetrisPlayer[] players) {
        gameState.setPlayers(players);
    }
    public void setGameState(GameStatePattern gameState) {
        this.gameState = gameState;
    }

    //----------------------------------------------------------

    public void run() {
        gameState.run();
    }
    public void stop() {
        gameState.stop();
    }
    public void start() {
        gameState.start();
    }
    public void win() {
        gameState.win();
    }
    public void loose() {
        gameState.loose();
    }
    public void pauseGame() {
        gameState.pauseGame();
    }
    public void resumeGame() {
        gameState.resumeGame();
    }

    public GameStatePattern getStateLost() {
        return stateLost;
    }
    public GameStatePattern getStatePaused() {
        return statePaused;
    }
    public GameStatePattern getStateRunning() {
        return stateRunning;
    }
    public GameStatePattern getStateStarting() {
        return stateStarting;
    }
    public GameStatePattern getStateStopped() {
        return stateStopped;
    }
    public GameStatePattern getStateWon() {
        return stateWon;
    }
}
