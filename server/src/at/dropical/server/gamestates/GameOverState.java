package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

public class GameOverState extends State{

    public GameOverState(Game game) {
        super(game);
    }

    @Override
    public GameDataContainer fillGameDataContainer(GameDataContainer gameDataContainer) {
        return null;
    }

    @Override
    public void handleInput(HandleInputRequest handleInputRequest, int playerNumber) {

    }
}
