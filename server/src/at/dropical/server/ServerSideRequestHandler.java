package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.server.gamestates.RunningState;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.transmitter.ServerSideTransmitter;
import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.RequestHandler;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.*;

import java.util.Map;
import java.util.logging.Level;

import static at.dropical.server.Server.LOGGER;

public class ServerSideRequestHandler implements RequestHandler {
    private Request request;
    private ServerSideTransmitter transmitter;

    public ServerSideRequestHandler(Request request, ServerSideTransmitter transmitter) {


//        LOGGER.log(Level.FINE,"Debug - Fine");
//        LOGGER.log(Level.INFO,"Debug - Info");
//        LOGGER.log(Level.WARNING,"Debug - Warning");
//        LOGGER.log(Level.SEVERE,"Debug - SEVERE");


        LOGGER.log(Level.INFO, "New Request to Handle");
        this.request = request;
        this.transmitter = transmitter;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (request instanceof CreateGameRequest) {
            handleCreateGameRequest((CreateGameRequest) request);
        } else if (request instanceof ListGamesRequest) {
            handleListGamesRequest();
        } else if (request instanceof JoinRequest) {
            handleJoinRequest((JoinRequest) request);
        } else if (request instanceof AddAiToGameRequest) {
            handleAddAiToGameRequest((AddAiToGameRequest) request);
        } else if (request instanceof HandleInputRequest) {
            handleInputDataContainer((HandleInputRequest) request);
        } else if (request instanceof StartGameRequest) {
            handleStartGameRequest((StartGameRequest) request);
        } else if (request instanceof ListPlayersRequest) {
            handleListPlayersRequest();
        }
    }

    public void handleCreateGameRequest(CreateGameRequest request) {
        LOGGER.log(Level.INFO, "Request to Handle is a CreateGameRequest");
        Server.instance().getAllGames().put(request.getGameName(), new Game());
    }

    public void handleListGamesRequest() {
        LOGGER.log(Level.INFO, "Request to Handle is a ListRequest");
        ListDataContainer listDataContainer = new ListDataContainer(GameState.GAME_LIST);

        for (Map.Entry<String, Game> stringGameEntry : Server.instance().getAllGames().entrySet()) {
            listDataContainer.addName(stringGameEntry.getKey());
        }
        transmitter.writeRequest(listDataContainer);
    }

    public void handleJoinRequest(JoinRequest request) {

        LOGGER.log(Level.INFO, "Request to Handle is a JoinRequest");

        //assign to a random game
        if (request.getGameID() == null) {
            if (Server.instance().getAllGames().size() > 0)
                for (Map.Entry<String, Game> games : Server.instance().getAllGames().entrySet()) {
                    if (games.getValue().getCurrentGameState() instanceof WaitingState) {
                        handleJoinRequest(new JoinRequest(games.getKey(), request.getPlayerName()));
                        return;
                    }
                }
            //if all else fails
            //create game yourself and join that game
            String gamename = "autoGen_"+request.getPlayerName() + "_game";
            handleCreateGameRequest(new CreateGameRequest(gamename));
            handleJoinRequest(new JoinRequest(gamename, request.getPlayerName()));
        }

        //when user is too fast & game is not yet created
        while (Server.instance().getGame(request.getGameID()) == null) ;
        Game game = Server.instance().getGame(request.getGameID());

//            if(game==null)
//                throw new RuntimeException("Game does not exist. Fuck Off.");       //polite msg for the moment,TODO: create new game instantely ?

        game.addPlayer(request.getPlayerName(), transmitter);  //TODO: send message to client ?
        transmitter.setPlayingGame(game);

    }

    public void handleAddAiToGameRequest(AddAiToGameRequest request) {
        LOGGER.log(Level.INFO, "Request to Handle is a AddAiToGameRequest");

        while (Server.instance().getGame(request.getGameID()) == null) ;
        Game game = Server.instance().getGame(request.getGameID());

//        if ((Server.isAiAllowed && game.getNumAI() == 0)||Server.isPureAiGameAllowed){               //FIXME: curr AI count < max player count
//
//
//            LocalServerTransmitter transmitter=new LocalServerTransmitter(new ServerInvokedAI("Rudi").getRequestCache());
//
//            transmitter.setPlayerNumber(game.addAI(transmitter));
//            transmitter.setPlayingGame(game);
//        }
    }

    public void handleInputDataContainer(HandleInputRequest request) {
        LOGGER.log(Level.INFO, "Request to Handle is a InputDataContainer");
        if (transmitter.getPlayingGame() != null) {
            transmitter.getPlayingGame().handleInput(request);
        }
    }

    public void handleStartGameRequest(StartGameRequest startGameRequest) {
        LOGGER.log(Level.INFO, "Request to Handle is a StartGameRequest");
        Game g = Server.instance().getGame(startGameRequest.getGameID());
        g.setCurrentGameState(new RunningState(g));
    }

    public void handleListPlayersRequest() {
        LOGGER.log(Level.INFO, "Request to Handle is a ListPlayersRequest");
        if (transmitter.getPlayingGame() != null) {
            ListDataContainer listDataContainer = new ListDataContainer(GameState.LOBBY);

            for (Map.Entry<String, OnePlayer> player : transmitter.getPlayingGame().getGames().entrySet()) {
                listDataContainer.addName(player.getKey());
            }
            transmitter.writeRequest(listDataContainer);
        }
    }
}
