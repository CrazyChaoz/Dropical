package at.dropical.shared.net.requests;

import at.dropical.shared.PlayerAction;
import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;

import java.util.PriorityQueue;
import java.util.Queue;

public class HandleInputInBulkRequest extends Request {
    private Queue<PlayerAction> input=new PriorityQueue<>();
    private String playername;

    public HandleInputInBulkRequest(String playername) {
        super(RequestKind.HANDLE_BULK_INPUT);
        this.playername = playername;
    }

    public void addPlayerAction(PlayerAction playerAction) {
        input.add(playerAction);
    }

    public Queue<PlayerAction> getInput() {
        return input;
    }

    public String getPlayername() {
        return playername;
    }
}
