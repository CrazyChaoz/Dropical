package com.dropical.client.screens;

import at.dropical.shared.GameState;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalButton;
import com.pezcraft.dropical.gui.DropicalTextField;

public class ServerList implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private BitmapFont bitmapFont;

    //Manager
    private ScreenManager screenManager;
    private DataManager manager;

    //TextField/Button
    private Stage stage;
    private DropicalTextField ipTextField;
    private DropicalButton localhostButton;

    private DropicalMain game;
    public ServerList(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Hintergrund
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        //Schrift für TextFields
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Kamera
        cam = new DropicalCam(1280, 720);

        //Manager
        manager = DataManager.getInstance();
        screenManager = ScreenManager.getInstance();

        //Dropical TextField, Buttons
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ipTextField = new DropicalTextField("IP", bitmapFont, 440, 250, 400, 88);
        ipTextField.setCursorTexture("GUI/textFields/textField_cursor.png",4, 14);
        ipTextField.setBackgroundTexture("GUI/textFields/textField_background.png", 100, 22);
        ipTextField.setSelectionTexture("GUI/textFields/textField_selection.png", 1, 1);
        ipTextField.setDisabledTexture("GUI/textFields/textField_disabled.png", 100, 22);
        ipTextField.setMaxLength(16);
        ipTextField.setTextFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return Character.isDigit(c) || c == '.';
            }
        });
        ipTextField.setTextAlignment(Align.center);
        ipTextField.setBlinkInterval(0.5f);

        localhostButton = new DropicalButton(524, 552, 232, 56);
        localhostButton.setUpTexture("GUI/buttons/hammock/hammock_up.png", 58, 14);
        localhostButton.setDownTexture("GUI/buttons/hammock/hammock_down.png", 58, 14);
        localhostButton.setOverTexture("GUI/buttons/hammock/hammock_down.png", 58, 14);
        localhostButton.setDisabledTexture("GUI/buttons/hammock/hammock_disabled.png", 58, 14);
        localhostButton.updateStyle();
        localhostButton.getButton().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                manager.createProxy("localhost");
                manager.joinMultiplayer();

                screenManager.setGameScreen(new Game(game, 2), game);
                screenManager.setCountdownScreen(new CountDown(game), game);
                screenManager.showScreen(screenManager.getCountdownScreen());

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(ipTextField.getField());
        stage.addActor(localhostButton.getButton());
        stage.setViewport(cam.getViewport());
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        //TextFieldevents
        stage.act(delta);

        game.getBatch().begin();

        //Hintergrundbild zeichnen
        background.draw(game.getBatch());

        game.getBatch().end();

        //DropicalTextFields rendern
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            manager.createProxy(ipTextField.getField().getText());
            manager.joinMultiplayer();

            screenManager.setLobbyScreen(new Lobby(game), game);
            screenManager.showScreen(screenManager.getLobbyScreen());
        }
    }

    @Override
    public void dispose() {

    }
}
