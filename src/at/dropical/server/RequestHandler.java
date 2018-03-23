package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.JoinRequest;
import at.dropical.shared.net.requests.ListRequest;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

import java.util.Map;

public class RequestHandler extends Thread {
    private Request request;
    private Transmitter transmitter;

    public RequestHandler(Request request, Transmitter transmitter) {
        this.request=request;
        this.transmitter=transmitter;

        this.start();
    }

    @Override
    public void run() {
        if(request instanceof ListRequest){
            if(((ListRequest) request).isGameListRequest()){
                for (Map.Entry<String, Game> stringGameEntry : Server.exe().getAllGames().entrySet()) {
                    ((ListRequest) request).addName(stringGameEntry.getKey());
                }
            }else{
                //TODO: Transmit Server selection
            }
            transmitter.writeRequest(request);
        }else if(request instanceof JoinRequest){

            Game game=Server.exe().getGame(((JoinRequest) request).getGameID());

            if(((JoinRequest) request).isPlayer())
                game.addPlayer(((JoinRequest) request).getPlayerName(),transmitter);
            else
                game.addViewer(transmitter);
        }
    }
}
