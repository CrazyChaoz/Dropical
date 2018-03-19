package at.dropical.shared.net.requests;

import at.dropical.shared.GameState;

public class UpdateUIRequest {
    private GameState state;
    private int[][][] arenas=new int[2][20][10];
    private int[][][] nextTrock=new int[2][4][4];
    private int level;
}
