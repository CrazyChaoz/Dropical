package at.dropical.client.impl;

import at.dropical.client.DropicalHandler;
import at.dropical.client.DropicalProxy;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.*;

import java.io.IOException;
import java.util.Scanner;

public class BestJavaClient implements DropicalHandler {
    private Scanner scanner=new Scanner(System.in);

    public BestJavaClient() throws IOException {
        DropicalProxy proxy=new DropicalProxy("localhost",45000,this);
        String playername;

        System.out.println("Whats your name?");
        playername=scanner.next();


        int i=1;
        while (i!=0){
            System.out.println("So, "+playername+", what do you want to do?");
            System.out.println("[1]: list currently open games");
            System.out.println("[2]: create new game");
            System.out.println("[3]: join a game");
            System.out.println("[4]: list connected players to your game");
            System.out.println("[5]: start a game");
            System.out.println("[6]: send input to the server");
            System.out.println("[7]: autoQueue");
            System.out.println("[8]: create a game with a specific number of players");
            i=scanner.nextInt();
            switch (i){
                case 1:
                    proxy.writeToServer(new ListGamesRequest());
                    break;
                case 2:
                    System.out.println("What should this game be called?");
                    proxy.writeToServer(new CreateGameRequest(scanner.next()));
                    break;
                case 3:
                    System.out.println("Which game do you want to join?");
                    proxy.writeToServer(new JoinRequest(scanner.next(),playername));
                    break;
                case 4:
                    proxy.writeToServer(new ListPlayersRequest());
                    break;
                case 5:
                    System.out.println("Which game do you want to start?");
                    proxy.writeToServer(new StartGameRequest(scanner.next()));
                    break;
                case 6:
                    System.out.println("What Input Key do you want to send?");
                    proxy.writeToServer(new HandleInputRequest(playername,getInput()));
                    break;
                case 7:
                    proxy.writeToServer(new JoinRequest(playername));
                    break;
                case 8:
                    System.out.println("What should this game be called?");
                    String gameName=scanner.next();
                    System.out.println("How many players should be able to play this game?");
                    int playerCount=scanner.nextInt();
                    proxy.writeToServer(new CreateGameRequest(gameName,playerCount));
                    break;
            }
        }
    }
    private PlayerAction getInput(){
        for (;;){
            switch (scanner.next()){
                case "w":
                    return PlayerAction.UP;
                case "a":
                    return PlayerAction.LEFT;
                case "s":
                    return PlayerAction.DOWN;
                case "d":
                    return PlayerAction.RIGHT;
                case " ":
                    return PlayerAction.DROP;

            }
        }
    }

    @Override
    public void handle(Container container) {
        if (container.getCurrentState() != null) {
            switch (container.getCurrentState()) {
                case LOBBY:
                    System.out.println("#########");
                    for (String s : ((ListDataContainer) container).getGameNames())
                        System.out.println("Spieler: " + s);
                    System.out.println("#########");
                    break;
                case GAME_LIST:
                    System.out.println("#########");
                    for (String s : ((ListDataContainer) container).getGameNames())
                        System.out.println("Game: " + s);
                    System.out.println("#########");
                    break;
                case PAUSE:
                case RUNNING:
                    System.out.println("GameDataContainer recieved");
                    break;
                case STARTING:
                    System.out.println("Time until Start: " + ((CountDownContainer) container).getSeconds());
                    break;
                case GAME_OVER:
                case GAME_WON:
                case GAME_LOST:
                    System.out.println("Game is Over");
                    break;

            }
        }
    }
}
