package com.dropical.client.gameStates;

import com.dropical.client.server.TetrisPlayer;
import com.dropical.client.serverEssentials.GameState;
import com.dropical.client.serverEssentials.PollRequest;

public class Won implements GameStatePattern {
    private StateOfGame stateOfGame;
    private TetrisPlayer[] players;

    public Won(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    //----------------------------------------------------------

    @Override
    public PollRequest buildPollRequest(PollRequest poll) {
        //aktueller Spieler
        TetrisPlayer actPlayer = players[poll.getPlayerNo()];

        //GameState-Enum updaten
        poll.setGameState(GameState.GAME_WON);

        //wenn der Spieler gewinnt, verliert der andere Spieler
        if(players.length == 2) {
            players[(poll.getPlayerNo()+1)%2].getStateOfGame().loose();
        }

        //Arena & Tetrominos sind noch leer
        poll.setArena(new int[20][10]);
        poll.setNextTetronimo(new int[4][4]);
        poll.setActTetronimo(new int[4][4]);
        poll.setActTetronimoX(actPlayer.getActTet().getPosX());
        poll.setActTetronimoY(actPlayer.getActTet().getPosY());

        //Punkte
        poll.setScore(actPlayer.getPoints());

        return poll;
    }

    @Override
    public void setPlayers(TetrisPlayer[] players) {
        this.players = players;
    }

    //----------------------------------------------------------

    @Override
    public void run() {

    }
    @Override
    public void stop() {
        stateOfGame.setGameState(stateOfGame.getStateStopped());
    }
    @Override
    public void start() {

    }
    @Override
    public void win() {

    }
    @Override
    public void loose() {

    }
    @Override
    public void pauseGame() {

    }
    @Override
    public void resumeGame() {

    }
}
