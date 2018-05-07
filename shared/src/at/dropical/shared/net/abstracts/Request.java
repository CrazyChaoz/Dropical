package at.dropical.shared.net.abstracts;

import at.dropical.shared.RequestKind;

public abstract class Request implements SendableItem {
    private RequestKind requestKind;

    public Request(RequestKind requestKind) {
        this.requestKind = requestKind;
    }

    public RequestKind getRequestKind() {
        return requestKind;
    }
}
