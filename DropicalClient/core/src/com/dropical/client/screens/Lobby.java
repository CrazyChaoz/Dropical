package com.dropical.client.screens;

import at.dropical.shared.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.world.Background;
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalButton;
import com.pezcraft.dropical.gui.DropicalTextField;

import java.util.List;

public class Lobby implements Screen {
    private DropicalCam cam;
    private BitmapFont bitmapFont;
    private Background background;

    //Manager
    private ScreenManager screenManager;
    private DataManager manager;
    private List<String> playerList;

    //TextField/Button
    private Stage stage;

    private DropicalMain game;
    public Lobby(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Hintergrund
        background = Background.getInstance();

        //Schrift
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Kamera
        cam = new DropicalCam(1280, 720);

        //Manager
        manager = DataManager.getInstance();
        screenManager = ScreenManager.getInstance();
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

//        playerList = manager.getListData().getNames();

        background.update();

        //----------------------------------------------------------

        game.getBatch().begin();

        //Hintergrund zeichnen
        background.draw(game.getBatch());

//        if(manager.getGameData().getCurrentState() != GameState.LOBBY) {
            screenManager.setGameScreen(new Game(game, 2), game);
            screenManager.setCountdownScreen(new CountDown(game), game);
            screenManager.showScreen(screenManager.getCountdownScreen());
//        }

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    private void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            screenManager.setMenuScreen(new Menu(game), game);
            screenManager.showScreen(new Menu(game));
        }
    }

    @Override
    public void dispose() {

    }
}
