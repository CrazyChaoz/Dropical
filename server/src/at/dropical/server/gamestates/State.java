package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public interface State {

    void fillGameDataContainer(OnePlayer player, GameDataContainer gameDataContainer);
    void handleInput(OnePlayer player, InputDataContainer inputDataContainer);
}

