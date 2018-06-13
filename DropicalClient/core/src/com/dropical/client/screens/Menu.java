package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalButton;

public class Menu implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private BitmapFont bitmapFont;

    //Manager
    private ScreenManager screenManager = ScreenManager.getInstance();
    private DataManager manager;

    //DropicalButton
    private Stage stage;
    private DropicalButton singleplayerButton;
    private DropicalButton multiplayerLocalButton;
    private DropicalButton multiplayerOnlineButton;
    private DropicalButton tournamentButton;
    private DropicalButton settingsButton;

    private DropicalButton testButton;

    //Mobs
    private Sprite fishSprite;

    private DropicalMain game;
    public Menu(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Hintergrund
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        //Schrift für Buttons
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Kamera
        cam = new DropicalCam(1280, 720);

        //Manager
        manager = DataManager.getInstance();

        //Mobs
        fishSprite = new Sprite(new Texture(Gdx.files.internal("Fish/fish.png")));
        fishSprite.setSize(56, 56);
        fishSprite.setPosition(600,260);
        fishSprite.rotate(-30);

        //DropicalButton
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Buttons
        singleplayerButton = new DropicalButton("Singleplayer", bitmapFont, 88, 12, 208, 244);
        singleplayerButton.setUpTexture("GUI/buttons/cat/cat_up.png", 52, 61);
        singleplayerButton.setDownTexture("GUI/buttons/cat/cat_down.png", 52, 61);
        singleplayerButton.setOverTexture("GUI/buttons/cat/cat_down.png", 52, 61);
        singleplayerButton.setDisabledTexture("GUI/buttons/cat/cat_disabled.png", 52, 61);
        singleplayerButton.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!singleplayerButton.getButton().isDisabled()) {
                    screenManager.setServerListScreen(new ServerList(game), game);
                    screenManager.showScreen(screenManager.getServerListScreen());

//                manager.createProxy();
//                manager.joinSingleplayer();
//
//                screenManager.setGameScreen(new Game(game, 1), game);
//                screenManager.setCountdownScreen(new CountDown(game), game);
//                screenManager.showScreen(screenManager.getCountdownScreen());
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

//        multiplayerLocalButton = new DropicalButton("local Multiplayer", bitmapFont, "GUI/buttons/map/map_up.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_disabled.png", 332, 28, 65, 44, 260, 176);
//        multiplayerLocalButton.getButton().addListener(new InputListener() {
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                if(!multiplayerLocalButton.isDisabled()) {
//                    manager.createProxy("localhost");
//                    manager.joinTurnier();
//
//                    screenManager.setGameScreen(new Game(game, 2), game);
//                    screenManager.setCountdownScreen(new CountDown(game), game);
//                    screenManager.showScreen(screenManager.getCountdownScreen());
//                }
//                return super.touchDown(event, x, y, pointer, button);
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                super.touchUp(event, x, y, pointer, button);
//            }
//        });
//        multiplayerLocalButton.getButton().setDisabled(true);
//        multiplayerOnlineButton = new DropicalButton("Multiplayer", bitmapFont, "GUI/buttons/map/map_up.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_disabled.png", 688, 28, 65, 44, 260, 176);
//        multiplayerOnlineButton.flipX();
//        multiplayerOnlineButton.getButton().setDisabled(true);
//        tournamentButton = new DropicalButton("Tournaments", bitmapFont, "GUI/buttons/cat/cat_up.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_disabled.png", 984, 12, 52, 61, 208, 244);
//        tournamentButton.flipX();
//        tournamentButton.getButton().setDisabled(true);

        settingsButton = new DropicalButton("Einstellungen", bitmapFont, 440, 250, 400, 88);
        settingsButton.setUpTexture("GUI/buttons/main/main_up.png", 100, 22);
        settingsButton.setDownTexture("GUI/buttons/main/main_down.png", 100, 22);
        settingsButton.setOverTexture("GUI/buttons/main/main_down.png", 100, 22);
        settingsButton.setDisabledTexture("GUI/buttons/main/main_disabled.png", 100, 22);
        settingsButton.updateStyle();
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!settingsButton.isDisabled()) {
                    screenManager.setSettingsScreen(new Settings(game), game);
                    screenManager.showScreen(screenManager.getSettingsScreen());
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(singleplayerButton.getButton());
//        stage.addActor(multiplayerLocalButton.getButton());
//        stage.addActor(multiplayerOnlineButton.getButton());
//        stage.addActor(tournamentButton.getButton());
        stage.addActor(settingsButton.getButton());
        stage.setViewport(cam.getViewport());
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        //Buttonevents
        stage.act(delta);

        //Hintergrundfarbe (weiß)
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //----------------------------------------------------------

        game.getBatch().begin();

        //Hintergrundbild zeichnen
        background.draw(game.getBatch());

        //Mobs zeichnen
        fishSprite.draw(game.getBatch());

        game.getBatch().end();

        //----------------------------------------------------------

        //DropicalButtons rendern
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            screenManager.setGameScreen(new Game(game, 1), game);
            screenManager.showScreen(screenManager.getGameScreen());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            screenManager.setGameScreen(new Game(game, 2), game);
            screenManager.showScreen(screenManager.getGameScreen());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
