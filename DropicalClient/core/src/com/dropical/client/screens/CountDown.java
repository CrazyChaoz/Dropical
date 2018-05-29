package com.dropical.client.screens;

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
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalButton;

public class CountDown implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private BitmapFont bitmapFont;

    //Manager
    ScreenManager screenManager = ScreenManager.getInstance();
    private DataManager manager;

    //Countdown
    private int countdown = 0;

    private DropicalMain game;
    public CountDown(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Hintergrund
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        //Schrift für Steuerung-Erklärung
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        manager = DataManager.getInstance();
        manager.createProxy();

        //Kamera
        cam = new DropicalCam(1280, 720);
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        game.getBatch().begin();

        //Hintergrundbild zeichnen
        background.draw(game.getBatch());

        //Countdown zeichnen
        if(manager.getCountDownContainer() != null) {
            countdown = manager.getCountDownContainer().getSeconds();
        }
        bitmapFont.draw(game.getBatch(), countdown+"", 0, 360, 1280, 1, false);

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
            screenManager.showScreen(screenManager.getMenuScreen());
        }
    }

    @Override
    public void dispose() {

    }
}
