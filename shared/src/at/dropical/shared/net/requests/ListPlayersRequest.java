package at.dropical.shared.net.requests;

import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class ListPlayersRequest extends Request {
    public ListPlayersRequest() {
        super(RequestKind.LIST_PLAYERS);
    }
}
