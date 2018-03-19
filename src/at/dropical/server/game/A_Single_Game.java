package at.dropical.server.game;

import at.dropical.server.Player;
import at.dropical.server.logicState.*;
import at.dropical.shared.GameState;
import org.jetbrains.annotations.NotNull;

//TODO: Besseren namen geben
public class A_Single_Game implements StateManager{

    private Player player;
    //private TetrisArena arena;
    //private Tetromino currTrock=Tetromino.createRandom();
    //private Tetromino nextTrock=Tetromino.createRandom();
    //private int currTrockX;
    //private int currTrockY;

    /** Only the state that is currently stored in
     * curState gets updated. */
    private RunningState runningGameLogic = new RunningState(this);
    private PausedState pausedGameLogic = new PausedState(this);
    private WonLostState wonLostGameLogic = new WonLostState(this, runningGameLogic);

    private GameLogicState curState = runningGameLogic;


    public A_Single_Game(Player player) {
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }


    public int[][] getNextTrock() {
        return curState.getNextTetromino().toArray();
    }

    @Override
    public void changeGameState(@NotNull GameState state) {
        switch (state) {
            case GAME_RUNNING:
                curState = runningGameLogic;
                break;
            case GAME_PAUSE:
                curState = pausedGameLogic;
                break;
            case GAME_WON:
                curState = wonLostGameLogic;
                break;
            case GAME_LOST:
                curState = wonLostGameLogic;
                break;
            case GAME_OVER:
                curState = wonLostGameLogic;
                break;
        }
    }

    @Override
    public void resetGame(){}
}
