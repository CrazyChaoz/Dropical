package at.dropical.client.ai;


import at.dropical.shared.net.requests.*;


public class SimpleAI extends ArtificialIntelligence {
    private String name;

    public SimpleAI(String name) {
        this.name = name;
    }

    private GameDataContainer currentGameDataContainer;


    @Override
    protected void handleRequest(Request r) {
        if (r instanceof GameDataContainer){
            System.out.println("Handle this GameDataContainer");
            currentGameDataContainer= (GameDataContainer) r;
        }
        else if (r instanceof ListRequest){
            System.out.println("Handle this ListRequest");
            for (String gameName : ((ListRequest) r).getGameNames()) {
                System.out.println(gameName);
            }
        }
    }

    @Override
    public void run() {
        //Things that Clients do
        toServer(new ListRequest(true));
        toServer(new CreateGameRequest("Super Secret Game"));

    }

    public static void main(String[] args) {
        new SimpleAI("Rudi");
    }
}
