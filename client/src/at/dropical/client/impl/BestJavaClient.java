package at.dropical.client.impl;

import at.dropical.client.Proxy;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.requests.*;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BestJavaClient {
    private static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Proxy proxy=new Proxy(new Socket("localhost",45000));
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
                    proxy.transmitToServer(new ListGamesRequest());
                    break;
                case 2:
                    System.out.println("What should this game be called?");
                    proxy.transmitToServer(new CreateGameRequest(scanner.next()));
                    break;
                case 3:
                    System.out.println("Which game do you want to join?");
                    proxy.transmitToServer(new JoinRequest(scanner.next(),playername));
                    break;
                case 4:
                    proxy.transmitToServer(new ListPlayersRequest());
                    break;
                case 5:
                    System.out.println("Which game do you want to start?");
                    proxy.transmitToServer(new StartGameRequest(scanner.next()));
                    break;
                case 6:
                    System.out.println("What Input Key do you want to send?");
                    proxy.transmitToServer(new HandleInputRequest(playername,getInput()));
                    break;
                case 7:
                    proxy.transmitToServer(new JoinRequest(playername));
                    break;
                case 8:
                    System.out.println("What should this game be called?");
                    String gameName=scanner.next();
                    System.out.println("How many players should be able to play this game?");
                    int playerCount=scanner.nextInt();
                    proxy.transmitToServer(new CreateGameRequest(gameName,playerCount));
                    break;
            }
        }
    }
    private static PlayerAction getInput(){
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
}
