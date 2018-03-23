package com.dropical.client.gameStates;

import com.badlogic.gdx.utils.TimeUtils;
import com.dropical.client.serverEssentials.GameState;
import com.dropical.client.serverEssentials.PollRequest;
import com.dropical.client.server.TetrisPlayer;

public class Starting implements GameStatePattern {
    private StateOfGame stateOfGame;
    private TetrisPlayer[] players;

    //Starting-Countdown
    private int[][][] starting = {
            {       //0
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {       //1
                    {0,0,0,0,0,0,6,6,6,0},
                    {0,0,0,0,0,6,6,6,6,0},
                    {0,0,0,0,6,6,6,6,6,0},
                    {0,0,0,6,6,6,0,6,6,0},
                    {0,0,6,6,6,0,0,6,6,0},
                    {0,6,6,6,0,0,0,6,6,0},
                    {0,6,6,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0},
                    {0,0,0,0,0,0,0,6,6,0}
            },
            {       //2
                    {0,0,7,7,7,7,7,7,0,0},
                    {0,7,7,7,7,7,7,7,7,0},
                    {7,7,7,0,0,0,0,7,7,7},
                    {7,7,0,0,0,0,0,0,7,7},
                    {0,0,0,0,0,0,0,0,7,7},
                    {0,0,0,0,0,0,0,0,7,7},
                    {0,0,0,0,0,0,0,7,7,7},
                    {0,0,0,0,0,0,0,7,7,0},
                    {0,0,0,0,0,0,7,7,7,0},
                    {0,0,0,0,7,7,7,7,0,0},
                    {0,0,0,7,7,7,7,0,0,0},
                    {0,0,7,7,7,0,0,0,0,0},
                    {0,0,7,7,0,0,0,0,0,0},
                    {0,7,7,7,0,0,0,0,0,0},
                    {0,7,7,0,0,0,0,0,0,0},
                    {7,7,7,0,0,0,0,0,0,0},
                    {7,7,0,0,0,0,0,0,0,0},
                    {7,7,0,0,0,0,0,0,0,0},
                    {7,7,7,7,7,7,7,7,7,7},
                    {7,7,7,7,7,7,7,7,7,7}
            },
            {       //3
                    {0,0,0,5,5,5,5,5,0,0},
                    {0,0,5,5,5,5,5,5,5,0},
                    {0,5,5,5,0,0,0,5,5,5},
                    {0,5,5,0,0,0,0,0,5,5},
                    {0,0,0,0,0,0,0,0,5,5},
                    {0,0,0,0,0,0,0,0,5,5},
                    {0,0,0,0,0,0,0,0,5,5},
                    {0,0,0,0,0,0,0,5,5,5},
                    {0,0,0,0,0,0,5,5,5,0},
                    {0,0,0,0,5,5,5,5,0,0},
                    {0,0,0,0,5,5,5,5,0,0},
                    {0,0,0,0,0,0,5,5,5,0},
                    {0,0,0,0,0,0,0,5,5,5},
                    {0,0,0,0,0,0,0,0,5,5},
                    {0,0,0,0,0,0,0,0,5,5},
                    {0,0,0,0,0,0,0,0,5,5},
                    {0,5,5,0,0,0,0,0,5,5},
                    {0,5,5,5,0,0,0,5,5,5},
                    {0,0,5,5,5,5,5,5,5,0},
                    {0,0,0,5,5,5,5,5,0,0}
            }

    };
    private int startingCounter;
    private long countdownStartTime;

    public Starting(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
        startingCounter = 4;
        countdownStartTime = 0; //Sollte eigentlich mit TimeUtils.nanoTime() intialisiert werden, sobald Starting Animation beginnt.
                                //Im Konstruktor kann man das aber nicht machen, da dies bereits bei der Anlegung der GameStates passiert.
                                //Deshalb mit 0 initialisieren, sodass timeSinceNanos ganz sicher eine Zahl größer als das Intervall liefert.
                                //In dem If wird dann TimeUtils.nanoTime() aufgerufen und alles funktioniert, wie es sein soll.
    }

    //----------------------------------------------------------

    @Override
    public PollRequest buildPollRequest(PollRequest poll) {
        //aktueller Spieler
        TetrisPlayer actPlayer = players[poll.getPlayerNo()];

        //GameState-Enum updaten
        poll.setGameState(GameState.GAME_STARTING);

        //Arena mit Countdown füllen
        long countdownInterval = 1000000000;    //jede Sekunden den Counter runterzählen
        if(TimeUtils.timeSinceNanos(countdownStartTime) > countdownInterval) {
            //wenn Countdown zu Ende, Spiel beginnen
            if(startingCounter == 1) {
                stateOfGame.run();
            }
            else {
                startingCounter--;
            }

            countdownStartTime = TimeUtils.nanoTime();
        }
        poll.setArena(starting[startingCounter]);

        //aktuell gibt es noch keine Tetrominos, deswegen leerer Array
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
        stateOfGame.setGameState(stateOfGame.getStateRunning());
    }
    @Override
    public void stop() {

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
