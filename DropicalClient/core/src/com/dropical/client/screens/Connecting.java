package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.pezcraft.dropical.animation.DropicalAnimation;
import com.pezcraft.dropical.cam.DropicalCam;

public class Connecting implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private BitmapFont bitmapFont;

    //Manager
    private ScreenManager screenManager;
    private DataManager manager;

    //TextField/Button
    private Stage stage;
    private String status;

    //Connecting Animation
    private Texture connecting;
    private TextureRegion[] connectingAnimationFrames;
    private DropicalAnimation connectingAnimation;
    private float connectingElapsedTime;

    private DropicalMain game;
    public Connecting(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        //Connecting-Animation zurücksetzen
        connectingElapsedTime = 0;

        //Hintergrund
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        //Schrift
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));



        //Connecting Animation (Game over)
        connecting = new Texture("GUI/loading/connectionAni.png");

        TextureRegion[][] connectionTmpFrames = TextureRegion.split(connecting, 84, 14);
        connectingAnimationFrames = new TextureRegion[5];
        int connectionIndex = 0;
        //Connection Animation vorwärts in Array speichern
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 1; j++) {
                connectingAnimationFrames[connectionIndex++] = connectionTmpFrames[i][j];
            }
        }
        connectingAnimation = new DropicalAnimation<TextureRegion>(1f/8f, connectingAnimationFrames);
        connectingAnimation.setScaling(4);
        connectingAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);



        //Kamera
        cam = new DropicalCam(1280, 720);

        //Manager
        manager = DataManager.getInstance();
        screenManager = ScreenManager.getInstance();

        status = "";
        try {
            manager.createProxy("localhost");
            manager.joinMultiplayer();
        } catch (Exception e) {
            status = "Fehler bei der Verbindung";
        }
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        //----------------------------------------------------------

        game.getBatch().begin();

        //Hintergrundbild zeichnen
        background.draw(game.getBatch());

        //Connecting Animation zeichnen
        renderConnectionAnimation();

        bitmapFont.draw(game.getBatch(), status, 0, 280, 1280, 1, false);

        if(manager.getListData() != null || manager.getCountDownContainer() != null) {
            screenManager.setLobbyScreen(new Lobby(game), game);
            screenManager.showScreen(screenManager.getLobbyScreen());
        }

        game.getBatch().end();
    }
    private void renderConnectionAnimation() {
        connectingElapsedTime += Gdx.graphics.getDeltaTime();
        connectingAnimation.draw(connectingElapsedTime, game.getBatch(), 472, 310);
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
            screenManager.showScreen(new Menu(game));
        }
    }

    @Override
    public void dispose() {

    }
}
