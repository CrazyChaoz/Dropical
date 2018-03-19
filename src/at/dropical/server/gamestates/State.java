package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public interface State{
    GameDataContainer fillGameDataContainer(Game game,GameDataContainer gameDataContainer);
    void handleInput(Game game,InputDataContainer inputDataContainer);
}
