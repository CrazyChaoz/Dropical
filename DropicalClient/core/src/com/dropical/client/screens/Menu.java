package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.npc.Crab;
import com.dropical.client.world.Background;
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalButton;

public class Menu implements Screen {
    private DropicalCam cam;
    private BitmapFont bitmapFont;
    private Background background;

    //Manager
    private ScreenManager screenManager = ScreenManager.getInstance();
    private DataManager manager;

    //DropicalButton
    private Stage stage;
    private DropicalButton singleplayerButton;
    private DropicalButton againstAIButton;
    private DropicalButton multiplayerOnlineButton;
    private DropicalButton tournamentButton;

    //NPCs
    private Crab crab;

    private DropicalMain game;
    public Menu(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Hintergrund
//        background = new Background(10);
        background = Background.getInstance();

        //Schrift für Buttons
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Kamera
        cam = new DropicalCam(1280, 720);

        //Manager
        manager = DataManager.getInstance();

        //DropicalButton
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Buttons
        singleplayerButton = new DropicalButton("Singleplayer", bitmapFont, 88, 12, 208, 244);
        singleplayerButton.setUpTexture("GUI/buttons/cat/cat_up.png", 52, 61);
        singleplayerButton.setDownTexture("GUI/buttons/cat/cat_down.png", 52, 61);
        singleplayerButton.setOverTexture("GUI/buttons/cat/cat_down.png", 52, 61);
        singleplayerButton.setDisabledTexture("GUI/buttons/cat/cat_disabled.png", 52, 61);
        singleplayerButton.setFontColor(new Color(0x4C4C4Cff));
        singleplayerButton.updateStyle();
        singleplayerButton.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!singleplayerButton.getButton().isDisabled()) {
                    manager.createProxy("localhost");
                    manager.joinSingleplayer();

                    screenManager.setGameScreen(new Game(game, 1), game);
                    screenManager.setCountdownScreen(new CountDown(game), game);
                    screenManager.showScreen(screenManager.getCountdownScreen());
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        againstAIButton = new DropicalButton("Player vs AI", bitmapFont, 332, 28, 260, 176);
        againstAIButton.setUpTexture("GUI/buttons/map/map_up.png", 65, 44);
        againstAIButton.setDownTexture("GUI/buttons/map/map_down.png", 65, 44);
        againstAIButton.setOverTexture("GUI/buttons/map/map_down.png", 65, 44);
        againstAIButton.setDisabledTexture("GUI/buttons/map/map_disabled.png", 65, 44);
        againstAIButton.setFontColor(new Color(0x4C4C4Cff));
        againstAIButton.updateStyle();
        againstAIButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!againstAIButton.isDisabled()) {
                    manager.createProxy("localhost");
                    manager.playAgainstAI();

                    screenManager.setGameScreen(new Game(game, 2), game);
                    screenManager.setCountdownScreen(new CountDown(game), game);
                    screenManager.showScreen(screenManager.getCountdownScreen());
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        multiplayerOnlineButton = new DropicalButton("Multiplayer", bitmapFont, 688, 28, 260, 176);
        multiplayerOnlineButton.setUpTexture("GUI/buttons/map/map_up.png",65, 44);
        multiplayerOnlineButton.setDownTexture("GUI/buttons/map/map_down.png", 65, 44);
        multiplayerOnlineButton.setOverTexture("GUI/buttons/map/map_down.png", 65, 44);
        multiplayerOnlineButton.setDisabledTexture("GUI/buttons/map/map_disabled.png", 65, 44);
        multiplayerOnlineButton.setFontColor(new Color(0x4C4C4Cff));
        multiplayerOnlineButton.flipX();
        multiplayerOnlineButton.updateStyle();
        multiplayerOnlineButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!multiplayerOnlineButton.isDisabled()) {
                    screenManager.setServerListScreen(new ServerList(game), game);
                    screenManager.showScreen(screenManager.getServerListScreen());
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        tournamentButton = new DropicalButton("Tournaments", bitmapFont, 984, 12, 208, 244);
        tournamentButton.setUpTexture("GUI/buttons/cat/cat_up.png", 52, 61);
        tournamentButton.setDownTexture("GUI/buttons/cat/cat_down.png", 52, 61);
        tournamentButton.setOverTexture("GUI/buttons/cat/cat_down.png", 52, 61);
        tournamentButton.setDisabledTexture("GUI/buttons/cat/cat_disabled.png", 52, 61);
        tournamentButton.setFontColor(new Color(0x4C4C4Cff));
        tournamentButton.flipX();
        tournamentButton.updateStyle();
        tournamentButton.setDisabled(true);

        crab = new Crab();
        crab.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                screenManager.setSettingsScreen(new Settings(game), game);
                screenManager.showScreen(screenManager.getSettingsScreen());

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(singleplayerButton.getButton());
        stage.addActor(againstAIButton.getButton());
        stage.addActor(multiplayerOnlineButton.getButton());
        stage.addActor(tournamentButton.getButton());
        stage.addActor(crab);
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

        background.update();

        //----------------------------------------------------------

        game.getBatch().begin();

        //Hintergrund zeichnen
        background.draw(game.getBatch());

        //Krabbe zeichnen
        crab.draw(game.getBatch());

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
