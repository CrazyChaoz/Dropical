package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pezcraft.dropical.cam.DropicalCam;
import com.dropical.client.client.DropicalMain;
import com.pezcraft.dropical.gui.DropicalButton;

public class Menu implements Screen {
    private DropicalCam cam;

    private Sprite background;
    private BitmapFont bitmapFont;

    //DropicalButton
    private Stage stage;
    private DropicalButton singleplayerButton;
    private DropicalButton multiplayerLocalButton;
    private DropicalButton multiplayerOnlineButton;
    private DropicalButton tournamentButton;

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

        //Schrift für Steuerung-Erklärung
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Kamera
        cam = new DropicalCam(1280, 720);

        //DropicalButton
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        singleplayerButton = new DropicalButton("Singleplayer", bitmapFont, "GUI/buttons/cat/cat_up.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_disabled.png", 88, 12, 52, 61, 208, 244);
        multiplayerLocalButton = new DropicalButton("local Multiplayer", bitmapFont, "GUI/buttons/map/map_up.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_disabled.png", 332, 28, 65, 44, 260, 176);
        multiplayerOnlineButton = new DropicalButton("Multiplayer", bitmapFont, "GUI/buttons/map/map_up.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_down.png", "GUI/buttons/map/map_disabled.png", 688, 28, 65, 44, 260, 176);
        multiplayerOnlineButton.flipX();
        tournamentButton = new DropicalButton("Tournaments", bitmapFont, "GUI/buttons/cat/cat_up.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_down.png", "GUI/buttons/cat/cat_disabled.png", 984, 12, 52, 61, 208, 244);
        tournamentButton.flipX();
        singleplayerButton.getButton().addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new Game(game, 1));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(singleplayerButton.getButton());
        stage.addActor(multiplayerLocalButton.getButton());
        stage.addActor(multiplayerOnlineButton.getButton());
        stage.addActor(tournamentButton.getButton());

        singleplayerButton.setDownTexture("GUI/background.png", 52, 61);
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

        //Eingabeerklärung zeichnen
        bitmapFont.draw(game.getBatch(), "[1] Singleplayer (Steuerung: WASD + SPACE)\n[2] Multiplayer (Steuerung: Pfeiltasten + ENTER)\n[P] Pause im Spiel", 0, 360, 1280, 1, false);

        game.getBatch().end();

        //DropicalButton
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.setScreen(new Game(game, 1));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.setScreen(new Game(game, 2));
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
