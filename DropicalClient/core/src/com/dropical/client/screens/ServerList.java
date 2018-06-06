package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.pezcraft.dropical.cam.DropicalCam;
import com.pezcraft.dropical.gui.DropicalTextField;

public class ServerList implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private BitmapFont bitmapFont;

    //Manager
    private ScreenManager screenManager = ScreenManager.getInstance();
    private DataManager manager;

    //TextField
    private Stage stage;
    private DropicalTextField ipTextField;

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

        //DropicaltextField
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        ipTextField = new DropicalTextField("", bitmapFont, "GUI/textFields/textField_background.png", "GUI/textFields/textField_cursor.png", 440, 250, 100, 22, 400, 88);

        stage.addActor(ipTextField.getField());
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
            manager.joinSingleplayer();

            screenManager.setGameScreen(new Game(game, 1), game);
            screenManager.setCountdownScreen(new CountDown(game), game);
            screenManager.showScreen(screenManager.getCountdownScreen());
        }
    }

    @Override
    public void dispose() {

    }
}
