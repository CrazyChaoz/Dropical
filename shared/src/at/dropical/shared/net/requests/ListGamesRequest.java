package at.dropical.shared.net.requests;

import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class ListGamesRequest extends Request {
    public ListGamesRequest() {
        super(RequestKind.LIST_GAMES);
    }
}
