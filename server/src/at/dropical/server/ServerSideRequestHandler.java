package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.server.gamestates.RunningState;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.transmitter.ServerToClientAdapter;
import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.RequestHandler;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.*;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;


public class ServerSideRequestHandler implements RequestHandler {
    private static Lock autoJoinLock = new ReentrantLock();
    private Request request;
    private ServerToClientAdapter transmitter;


    @Override
    public void run() {
        /*try {
            Thread.sleep(30);
        } catch(InterruptedException ignored) {
        }*/

        switch (request.getRequestKind()) {
            case LIST_GAMES:
                handleListGamesRequest();
                break;
            case CREATE_GAME:
                handleCreateGameRequest((CreateGameRequest) request);
                break;
            case HANDLE_INPUT:
                handleHandleInputRequest((HandleInputRequest) request);
                break;
            case LIST_PLAYERS:
                handleListPlayersRequest();
                break;
        }
    }

    private void handleCreateGameRequest(CreateGameRequest request) {
        Server.log(Level.INFO, "Request to Handle is a CreateGameRequest");
        Server.instance().getAllGames().put(request.getGameName(), new Game(request.getMaxPlayers()));
    }

    private void handleListGamesRequest() {
        Server.log(Level.INFO, "Request to Handle is a ListGamesRequest");
        ListDataContainer listDataContainer = new ListDataContainer(GameState.GAME_LIST);

        for (Map.Entry<String, Game> stringGameEntry : Server.instance().getAllGames().entrySet()) {
            listDataContainer.addName(stringGameEntry.getKey());
        }
        transmitter.writeRequest(listDataContainer);
    }

    private void handleJoinRequest(JoinRequest request) {
        autoJoinLock.lock();
        try {

            Server.log(Level.INFO, "Request to Handle is a JoinRequest");

            //assign to a random game
            if(request.getGameID() == null) {
                if(Server.instance().getAllGames().size() > 0) {
                    for(Map.Entry<String, Game> games : Server.instance().getAllGames().entrySet()) {
                        if(games.getValue().getCurrentGameState() instanceof WaitingState) {
                            handleJoinRequest(new JoinRequest(games.getKey(), request.getPlayerName()));
                            return;
                        }
                    }
                }
                //if all else fails
                //create game yourself and join that game
                String gamename = "autoGen_" + request.getPlayerName() + "_game";
                handleCreateGameRequest(new CreateGameRequest(gamename));
                handleJoinRequest(new JoinRequest(gamename, request.getPlayerName()));
            }

            //when user is too fast & game is not yet created
            while(Server.instance().getGame(request.getGameID()) == null) {
                try {
                    Thread.sleep(10);
                } catch(InterruptedException ignored) { }
            }
            Game game = Server.instance().getGame(request.getGameID());

            game.addPlayer(request.getPlayerName(), transmitter);  //TODO: send message to client ?
            transmitter.setPlayingGame(game);

            if(request.wantsToPlayAgainsAI()) {
            }

        } finally {
            autoJoinLock.unlock();
        }
    }

    private void handleHandleInputRequest(HandleInputRequest request) {
        Server.log(Level.INFO, "Request to Handle is a InputHandle");
        if (transmitter.getPlayingGame() != null) {
            transmitter.getPlayingGame().handleInput(request);
        }
    }

    private void handleStartGameRequest(StartGameRequest startGameRequest) {
        Server.log(Level.INFO, "Request to Handle is a StartGameRequest");
        Game g = Server.instance().getGame(startGameRequest.getGameID());
        g.setCurrentGameState(new RunningState(g));
    }

    private void handleListPlayersRequest() {
        Server.log(Level.INFO, "Request to Handle is a ListPlayersRequest");
        if (transmitter.getPlayingGame() != null) {
            ListDataContainer listDataContainer = new ListDataContainer(GameState.LOBBY);

            for (Map.Entry<String, OnePlayer> player : transmitter.getPlayingGame().getGames().entrySet()) {
                listDataContainer.addName(player.getKey());
            }
            transmitter.writeRequest(listDataContainer);
        }
    }
}
