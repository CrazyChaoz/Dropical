package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.world.Background;
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalButton;

public class Settings implements Screen {
    private DropicalCam cam;
    private BitmapFont bitmapFont;
    private Background background;

    //Manager
    private ScreenManager screenManager;
    private Preferences settings;

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
        background = Background.getInstance();

        //Schrift für Steuerung-Erklärung
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Kamera
        cam = new DropicalCam(1280, 720);

        //Manager
        screenManager = ScreenManager.getInstance();

        //Buttons
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ghostButton = new DropicalButton("Ghost", bitmapFont, 440, 250, 400, 88);
        ghostButton.setUpTexture("GUI/buttons/main/main_up.png", 100, 22);
        ghostButton.setDownTexture("GUI/buttons/main/main_down.png", 100, 22);
        ghostButton.setOverTexture("GUI/buttons/main/main_down.png", 100, 22);
        ghostButton.setCheckedTexture("GUI/buttons/main/main_down.png", 100, 22);
        ghostButton.setDisabledTexture("GUI/buttons/main/main_disabled.png", 100, 22);
        ghostButton.updateStyle();
        ghostButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(settings.getBoolean("ghost")) {
                    settings.putBoolean("ghost", false);
                }
                else {
                    settings.putBoolean("ghost", true);
                }
                settings.flush();

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(ghostButton.getButton());
        stage.setViewport(cam.getViewport());

        loadSettings();
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        //Buttonevents
        stage.act(delta);

        background.update();

        //----------------------------------------------------------

        game.getBatch().begin();

        //Hintergrund zeichnen
        background.draw(game.getBatch());

        game.getBatch().end();

        //----------------------------------------------------------

        //Buttons rendern
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        cam.update(width, height);
        stage.getViewport().update(width, height);
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

    private void loadSettings() {
        settings = Gdx.app.getPreferences("settings");

        if(settings.getBoolean("ghost")) {
            ghostButton.setChecked(true);
        }
    }

    @Override
    public void dispose() {

    }
}
