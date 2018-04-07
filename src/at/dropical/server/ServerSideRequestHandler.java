package at.dropical.server;

import at.dropical.client.ai.ServerInvokedAI;
import at.dropical.server.game.Game;
import at.dropical.shared.net.handler.RequestHandler;
import at.dropical.shared.net.requests.*;
import at.dropical.shared.net.transmitter.LocalServerTransmitter;
import at.dropical.shared.net.transmitter.Transmitter;

import java.util.Map;

public class ServerSideRequestHandler implements RequestHandler {
    private Request request;
    private Transmitter transmitter;

    public ServerSideRequestHandler(Request request, Transmitter transmitter) {

        System.out.println("new Request to Handle");
        this.request = request;
        this.transmitter = transmitter;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (request instanceof CreateGameRequest) {
            System.out.println("CreateGameRequest Request");
//            if(Server.instance().getGame(((CreateGameRequest) request).getGameName())==null)
                Server.instance().getAllGames().put(((CreateGameRequest) request).getGameName(),new Game());
        }else if (request instanceof ListRequest) {
            System.out.println("List Request");
            if (((ListRequest) request).isGameListRequest()) {
                for (Map.Entry<String, Game> stringGameEntry : Server.instance().getAllGames().entrySet()) {
                    ((ListRequest) request).addName(stringGameEntry.getKey());
                }
            } else {
                //TODO: Transmit Server list
            }
            transmitter.writeRequest(request);
        } else if (request instanceof JoinRequest) {
            System.out.println("JoinRequest Request");

            //when user is too fast & game is not yet created
            while (Server.instance().getGame(((JoinRequest) request).getGameID())==null);
            Game game = Server.instance().getGame(((JoinRequest) request).getGameID());

//            if(game==null)
//                throw new RuntimeException("Game does not exist. Fuck Off.");       //polite msg for the moment, create new game instantely ?

            if (((JoinRequest) request).isPlayer()) {
                int playerNumber=game.addPlayer(((JoinRequest) request).getPlayerName(), transmitter);  //TODO: send message to client ?
                transmitter.setPlayingGame(game);
                transmitter.setPlayerNumber(playerNumber);
            } else
                game.addViewer(transmitter);
        } else if (request instanceof AddAiToGameRequest) {
            System.out.println("AddAiToGameRequest Request");

            while (Server.instance().getGame(((AddAiToGameRequest) request).getGameID())==null);
            Game game = Server.instance().getGame(((AddAiToGameRequest) request).getGameID());

            if ((Server.isAiAllowed && game.getNumAI() == 0)||Server.isPureAiGameAllowed){               //FIXME: curr AI count < max player count


                LocalServerTransmitter transmitter=new LocalServerTransmitter(new ServerInvokedAI("Rudi").getRequestCache());

                transmitter.setPlayerNumber(game.addAI(transmitter));
                transmitter.setPlayingGame(game);
            }

        } else if (request instanceof InputDataContainer) {
            if(transmitter.getPlayingGame()!=null){
                transmitter.getPlayingGame().handleInput((InputDataContainer) request,transmitter.getPlayerNumber());
            }
        }
    }
}
