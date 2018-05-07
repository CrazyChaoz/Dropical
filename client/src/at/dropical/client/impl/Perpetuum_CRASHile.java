package at.dropical.client.impl;

import at.dropical.client.DropicalListener;
import at.dropical.client.DropicalProxy;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;
import at.dropical.shared.net.requests.JoinRequest;

import java.io.IOException;

public class Perpetuum_CRASHile {

    private final DropicalProxy proxy;

    private Perpetuum_CRASHile(String name,int inputs) throws IOException {

        proxy = new DropicalProxy("localhost", 45000, new DropicalListener() {
            @Override
            public void countDown(CountDownContainer container) {}

            @Override
            public void updateUI(GameDataContainer container) {
                for (int i = 0; i < inputs; i++) {
                    proxy.writeToServer(new HandleInputRequest(name,PlayerAction.UP));
                }
            }

            @Override
            public void somebodyJoinedTheLobby(ListDataContainer container) {}

            @Override
            public void onGameOver(GameOverContainer container) {}
        });
        proxy.writeToServer(new JoinRequest(name));
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 50; i++) {
            new Perpetuum_CRASHile("Crasher: "+i,1);
        }
    }
}
