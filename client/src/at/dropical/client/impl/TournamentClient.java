package at.dropical.client.impl;

import at.dropical.client.DropicalProxy;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.requests.*;

import java.io.IOException;
import java.util.Scanner;

public class TournamentClient {
    private Scanner scanner = new Scanner(System.in);

    public TournamentClient() throws IOException {
        DropicalProxy proxy = new DropicalProxy("localhost", 45000, new BestJavaListener());

        String playername;

        System.out.println("Whats your name?");
        playername = scanner.next();


        int i = 1;
        while (i != 0) {
            System.out.println("So, " + playername + ", what do you want to do?");
            System.out.println("[1]: send input to the server");
            System.out.println("[2]: autoQueue");
            i = scanner.nextInt();
            switch (i) {
                case 1:
                    System.out.println("What Input Key do you want to send?");
                    proxy.writeToServer(new HandleInputRequest(playername, getInput()));
                    break;
                case 2:
                    proxy.writeToServer(new JoinRequest(playername));
                    break;
            }
        }
    }

    private PlayerAction getInput() {
        for (; ; ) {
            switch (scanner.next()) {
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

    public static void main(String[] args) throws IOException {
        new TournamentClient();
    }

}
