package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.pezcraft.dropical.cam.DropicalCam;
import com.dropical.client.client.DropicalMain;

public class Menu implements Screen {
    private DropicalCam cam;

    private Sprite background;
    private BitmapFont bitmapFont;

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
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

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

    }
}
