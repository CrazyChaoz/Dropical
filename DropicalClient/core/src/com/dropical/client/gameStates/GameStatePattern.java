package com.dropical.client.gameStates;

import com.dropical.client.serverEssentials.PollRequest;
import com.dropical.client.server.TetrisPlayer;

public interface GameStatePattern {
    public void run();

    public void stop();
    public void start();

    public void win();
    public void loose();

    public void pauseGame();
    public void resumeGame();

    PollRequest buildPollRequest(PollRequest poll);
    public void setPlayers(TetrisPlayer[] players);
}
