package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.*;
import at.dropical.AI.AiTransmitter;
import at.dropical.shared.net.transmitter.Transmitter;

import java.util.Map;

public class RequestHandler extends Thread {
    private Request request;
    private Transmitter transmitter;

    public RequestHandler(Request request, Transmitter transmitter) {
        this.request = request;
        this.transmitter = transmitter;

        this.start();
    }

    @Override
    public void run() {
        if (request instanceof ListRequest) {
            if (((ListRequest) request).isGameListRequest()) {
                for (Map.Entry<String, Game> stringGameEntry : Server.instance().getAllGames().entrySet()) {
                    ((ListRequest) request).addName(stringGameEntry.getKey());
                }
            } else {
                //TODO: Transmit Server selection
            }
            transmitter.writeRequest(request);
        } else if (request instanceof JoinRequest) {

            Game game = Server.instance().getGame(((JoinRequest) request).getGameID());

            if (((JoinRequest) request).isPlayer()) {
                int playerNumber=game.addPlayer(((JoinRequest) request).getPlayerName(), transmitter); //TODO: send message to client ?
                transmitter.setPlayingGame(game);
                transmitter.setPlayerNumber(playerNumber);
            } else
                game.addViewer(transmitter);
        } else if (request instanceof AddAiToGameRequest) {

            Game game = Server.instance().getGame(((AddAiToGameRequest) request).getGameID());

            if ((Server.isAiAllowed && game.getNumAI() == 0)||Server.isPureAiGameAllowed){               //FIXME: curr AI count < max player count
                AiTransmitter transmitter=new AiTransmitter();
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
