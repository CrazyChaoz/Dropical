package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.server.transmitter.ServerTransmitter;
import at.dropical.shared.net.handler.RequestHandler;
import at.dropical.shared.net.requests.*;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;

import static at.dropical.server.Server.LOGGER;

public class ServerSideRequestHandler implements RequestHandler {
    private Request request;
    private ServerTransmitter transmitter;

    public ServerSideRequestHandler(Request request, ServerTransmitter transmitter) {


//        LOGGER.log(Level.FINE,"Debug - Fine");
//        LOGGER.log(Level.INFO,"Debug - Info");
//        LOGGER.log(Level.WARNING,"Debug - Warning");
//        LOGGER.log(Level.SEVERE,"Debug - SEVERE");


        LOGGER.log(Level.INFO,"New Request to Handle");
        this.request = request;
        this.transmitter = transmitter;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (request instanceof CreateGameRequest) {
           handleCreateGameRequest((CreateGameRequest) request);
        }else if (request instanceof ListRequest) {
            handleListRequest((ListRequest) request);
        } else if (request instanceof JoinRequest) {
            handleJoinRequest((JoinRequest) request);
        } else if (request instanceof AddAiToGameRequest) {
            handleAddAiToGameRequest((AddAiToGameRequest) request);
        } else if (request instanceof HandleInputRequest) {
            handleInputDataContainer((HandleInputRequest) request);
        }
    }

    public void handleCreateGameRequest(CreateGameRequest request){
        LOGGER.log(Level.INFO,"Request to Handle is a CreateGameRequest");
        Server.instance().getAllGames().put(request.getGameName(),new Game());
    }

    public void handleListRequest(ListRequest request){
        LOGGER.log(Level.INFO,"Request to Handle is a ListRequest");
        ListDataContainer listDataContainer=new ListDataContainer();
        if (request.isGameListRequest()) {
            for (Map.Entry<String, Game> stringGameEntry : Server.instance().getAllGames().entrySet()) {
                listDataContainer.addName(stringGameEntry.getKey());
            }
        } else {
            //TODO: Transmit Server list
        }
        try {
            transmitter.writeRequest(listDataContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param request
     */
    public void handleJoinRequest(JoinRequest request){

        LOGGER.log(Level.INFO,"Request to Handle is a JoinRequest");

        //when user is too fast & game is not yet created
        while (Server.instance().getGame(request.getGameID())==null);
        Game game = Server.instance().getGame(request.getGameID());

//            if(game==null)
//                throw new RuntimeException("Game does not exist. Fuck Off.");       //polite msg for the moment, create new game instantely ?

        if (request.isPlayer()) {
            int playerNumber=game.addPlayer(request.getPlayerName(), transmitter);  //TODO: send message to client ?
            transmitter.setCurrGame(game);
            transmitter.setPlayerNum(playerNumber);
        } else
            game.addViewer(transmitter);
    }

    public void handleAddAiToGameRequest(AddAiToGameRequest request){
        LOGGER.log(Level.INFO,"Request to Handle is a AddAiToGameRequest");

        while (Server.instance().getGame(request.getGameID())==null);
        Game game = Server.instance().getGame(request.getGameID());

//        if ((Server.isAiAllowed && game.getNumAI() == 0)||Server.isPureAiGameAllowed){               //FIXME: curr AI count < max player count
//
//
//            //TODO: LocalServerTransmitter transmitter=new LocalServerTransmitter(new ServerInvokedAI("Rudi").getRequestCache());
//
//            transmitter.setPlayerNum(game.addAI(transmitter));
//            transmitter.setCurrGame(game);
//        }
    }

    public void handleInputDataContainer(HandleInputRequest request){
        LOGGER.log(Level.INFO,"Request to Handle is a HandleInputRequest");
        if(transmitter.getCurrGame()!=null){
            transmitter.getCurrGame().handleInput(request,transmitter.getPlayerNum());
        }
    }
}
