package at.dropical.client.impl;

import at.dropical.client.DropicalListener;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;

public class BestJavaListener implements DropicalListener {
    @Override
    public void countDown(CountDownContainer container) {
        System.out.println("Starting");
        for (String s : container.getPlayernames()) {
            System.out.println("Players: "+s);
        }
        System.out.println("Zeit bis zum Start: " + container.getSeconds());

    }

    @Override
    public void updateUI(GameDataContainer container) {
        System.out.println("### Arenas ###");
        int i = 0;
        for (int[][] arenas : container.getArenas()) {
            System.out.println("### Arena von " + container.getPlayernames().get(i++) + " ###");
            for (int[] rows : arenas) {
                for (int cell : rows) {
                    System.out.print(cell);
                }
                System.out.println();
            }
            System.out.println();

        }
    }

    @Override
    public void somebodyJoinedTheLobby(ListDataContainer container) {
        System.out.println("Lobby");
        for (String s :  container.getNames()) {
            System.out.println("Players: "+s);
        }
    }

    @Override
    public void onGameOver(GameOverContainer container) {
        System.out.println("Game is Over");
    }
}
