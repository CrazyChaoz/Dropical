package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.managers.SettingsManager;
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalButton;
import com.pezcraft.dropical.gui.DropicalTextField;

public class Settings implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private BitmapFont bitmapFont;

    //Manager
    private ScreenManager screenManager = ScreenManager.getInstance();
    private SettingsManager settingsManager;

    //Button
    private Stage stage;
    private DropicalButton ghostButton;

    private DropicalMain game;
    public Settings(DropicalMain game) {
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

        //Manager
        settingsManager = SettingsManager.getInstance();

        //Kamera
        cam = new DropicalCam(1280, 720);

        //Buttons
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ghostButton = new DropicalButton("Ghost", bitmapFont, "GUI/buttons/main/main_up.png", "GUI/buttons/main/main_down.png", "GUI/buttons/main/main_down.png", "GUI/buttons/main/main_down.png", "GUI/buttons/main/main_disabled.png", 524, 552, 100, 22, 400, 88);
        ghostButton.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                settingsManager.setGhostActive(true);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(ghostButton.getButton());
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        //Buttonevents
        stage.act(delta);

        game.getBatch().begin();

        //Hintergrundbild zeichnen
        background.draw(game.getBatch());

        game.getBatch().end();

        //Buttons rendern
        stage.draw();
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
