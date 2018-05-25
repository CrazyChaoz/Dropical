package at.dropical.client;

import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;

public interface DropicalListener {
    void countDown(CountDownContainer container);
    void updateUI(GameDataContainer container);
    void somebodyJoinedTheLobby(ListDataContainer container);
    void onGameOver(GameOverContainer container);
}
