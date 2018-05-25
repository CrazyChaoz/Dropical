package com.dropical.client.gameStates;

import com.dropical.client.server.TetrisPlayer;
import com.dropical.client.serverEssentials.GameState;
import com.dropical.client.serverEssentials.PollRequest;

public class Running implements GameStatePattern {
    private StateOfGame stateOfGame;
    private TetrisPlayer[] players;

    public Running(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    //----------------------------------------------------------

    @Override
    public PollRequest buildPollRequest(PollRequest poll) {
        //aktueller Spieler
        TetrisPlayer actPlayer = players[poll.getPlayerNo()];

        //wenn Running vom State Paused aufgerufen wurde -> anderen Spieler ebenfalls resumen
        if(players.length == 2 && poll.getGameState() == GameState.GAME_PAUSED) {
            players[(poll.getPlayerNo()+1)%2].getStateOfGame().resumeGame();
        }

        //GameState-Enum updaten
        poll.setGameState(GameState.GAME_RUNNING);

        //Arena & Tetrominos füllen
        poll.setArena(actPlayer.getTetrisArena().toArray());
        poll.setNextTetronimo(actPlayer.getNextTet().toArray());
        poll.setActTetronimo(actPlayer.getActTet().toArray());
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

    }
    @Override
    public void start() {

    }
    @Override
    public void win() {
        stateOfGame.setGameState(stateOfGame.getStateWon());
    }
    @Override
    public void loose() {
        stateOfGame.setGameState(stateOfGame.getStateLost());
    }
    @Override
    public void pauseGame() {
        stateOfGame.setGameState(stateOfGame.getStatePaused());
    }
    @Override
    public void resumeGame() {

    }
}
