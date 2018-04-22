package at.dropical.client;

import at.dropical.shared.net.requests.CreateGameRequest;
import at.dropical.shared.net.requests.JoinRequest;
import at.dropical.shared.net.requests.ListRequest;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BestJavaClient {
    public static void main(String[] args) throws IOException {
        Proxy proxy=new Proxy(new Socket("localhost",45000));
        Scanner scanner=new Scanner(System.in);

        int i=1;
        while (i!=0){
            System.out.println("What do you want to do?");
            System.out.println("[1]: list currently open games");
            System.out.println("[2]: create new game");
            System.out.println("[3]: join a game");
            i=scanner.nextInt();
            switch (i){
                case 1:
                    proxy.transmitToServer(new ListRequest(true));
                    break;
                case 2:
                    System.out.println("What should this game be called?");
                    proxy.transmitToServer(new CreateGameRequest(scanner.next()));
                    break;
                case 3:
                    System.out.println("Which game do you want to join?");
                    proxy.transmitToServer(new JoinRequest(scanner.next()));
                    break;
            }
        }
    }
}
