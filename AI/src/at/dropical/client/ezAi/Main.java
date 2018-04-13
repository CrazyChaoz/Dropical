package at.dropical.client.ezAi;

import at.dropical.client.human.RemoteClient;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.requests.*;

import java.io.IOException;
import java.net.Socket;

public class Main extends RemoteClient {


    public Main(Socket server) throws IOException {
        super(server);
    }

    @Override
    protected void handleRequest(Request request) {
        if(request instanceof GameDataContainer){
            calculate((GameDataContainer) request);
        }else if(request instanceof ListDataContainer){

        }
    }

    @Override
    public void run() {
        toServer(new JoinRequest("asdf","Player 1"));
        InputDataContainer inputDataContainer=new InputDataContainer("Player 1");

        if(inputDataContainer!=null)
            toServer(inputDataContainer);
    }

    private void calculate(GameDataContainer gdc){
//        gdc.getArenas()[gdc.]
    }

    public static void main(String[] args) throws IOException {
        new Main(new Socket("localhost",45000));
    }
}
