package com.dropical.client.managers;

import com.badlogic.gdx.Screen;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.screens.Game;
import com.dropical.client.screens.GameOver;
import com.dropical.client.screens.Menu;

public class ScreenManager {
    private DropicalMain main;
    private Menu menuScreen;
    private Game gameScreen;
    private GameOver gameOverScreen;

    private static ScreenManager ourInstance = new ScreenManager();
    public static ScreenManager getInstance() {
        return ourInstance;
    }

    private ScreenManager() { }

    public void showScreen(Screen screen) {
        main.setScreen(screen);
    }

    public DropicalMain getMain() {
        return main;
    }
    public void setMain(DropicalMain main) {
        this.main = main;
    }
    public Menu getMenuScreen() {
        return menuScreen;
    }
    public void setMenuScreen(Menu menuScreen) {
        this.menuScreen = menuScreen;
    }
    public Game getGameScreen() {
        return gameScreen;
    }
    public void setGameScreen(Game gameScreen) {
        this.gameScreen = gameScreen;
    }
    public GameOver getGameOverScreen() {
        return gameOverScreen;
    }
    public void setGameOverScreen(GameOver gameOverScreen) {
        this.gameOverScreen = gameOverScreen;
    }
}
