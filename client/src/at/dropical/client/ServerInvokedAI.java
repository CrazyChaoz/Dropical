package at.dropical.client;


import at.dropical.shared.net.requests.*;


public class ServerInvokedAI {
    private String name;

    public ServerInvokedAI(String name)
        this.name = name;
    }

    private GameDataContainer currentGameDataContainer;


    @Override
    protected void handleRequest(Request r) {
        if (r instanceof GameDataContainer){
            System.out.println("Handle this GameDataContainer");
            currentGameDataContainer= (GameDataContainer) r;
        }
        else if (r instanceof ListDataContainer){
            System.out.println("Handle this ListRequest");
            for (String gameName : ((ListDataContainer) r).getGameNames()) {
                System.out.println(gameName);
            }
        }
    }

    @Override
    public void run() {
        //Things that Clients do
        toServer(new ListRequest(true));
        toServer(new CreateGameRequest("Super Secret Game"));
        toServer(new JoinRequest("TestGame",name));

    }
}
