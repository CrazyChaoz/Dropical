package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.world.Background;
import com.pezcraft.dropical.cam.DropicalCam;

public class CountDown implements Screen {
    private DropicalCam cam;
    private BitmapFont bitmapFont;
    private Background background;

    //Manager
    private ScreenManager screenManager = ScreenManager.getInstance();
    private DataManager manager;

    //Countdown
    private int countdown = 0;

    private DropicalMain game;
    public CountDown(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);

        //Hintergrund
        background = Background.getInstance();

        //Schrift für Steuerung-Erklärung
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(1.5f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        manager = DataManager.getInstance();

        //Kamera
        cam = new DropicalCam(1280, 720);
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        background.update();

        //----------------------------------------------------------

        game.getBatch().begin();

        //Hintergrund zeichnen
        background.draw(game.getBatch());

        //Countdown zeichnen
        if(manager.getCountDownContainer() != null) {
            countdown = manager.getCountDownContainer().getSeconds();
        }
        bitmapFont.draw(game.getBatch(), countdown+"", 0, 360, 1280, 1, false);

        if(manager.getGameData() != null) {
            screenManager.showScreen(screenManager.getGameScreen());
        }

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        cam.update(width, height);
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
            screenManager.showScreen(screenManager.getMenuScreen());
        }
    }

    @Override
    public void dispose() {

    }
}
