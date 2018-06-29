package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.dropical.client.world.Background;
import com.pezcraft.dropical.cam.DropicalCam;

import java.util.ArrayList;
import java.util.List;

public class Lobby implements Screen {
    private DropicalCam cam;
    private BitmapFont bitmapFont;
    private Background background;

    //Manager
    private ScreenManager screenManager;
    private DataManager manager;
    private List<String> playerList;

    //TextField/Button
    private Stage stage;

    //PlayerList Table
    private Sprite tableBackground;
    private Table table;
    private Table containerTable;
    private ScrollPane scrollPane;
    private List<String> names;

    private DropicalMain game;
    public Lobby(DropicalMain game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(null);

        //Hintergrund
        background = Background.getInstance();

        //Schrift
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Kamera
        cam = new DropicalCam(1280, 720);

        //Manager
        manager = DataManager.getInstance();
        screenManager = ScreenManager.getInstance();

        //PlayerList Table
        tableBackground = new Sprite(new Texture("GUI/table/table_background.png"));
        tableBackground.setSize(552, 624);
        tableBackground.setPosition(372, 56);

        table = new Table();
        containerTable = new Table();
        scrollPane = new ScrollPane(table);
        containerTable.setBounds(372, 72, 552, 544);
        containerTable.add(scrollPane).width(552).height(544);
        containerTable.row();

        names = new ArrayList<String>();
        table.top();
        table.setDebug(true);

        stage = new Stage();
        stage.addActor(containerTable);
        stage.setViewport(cam.getViewport());
    }

    @Override
    public void render(float delta) {
        //Tastatureingaben
        handleInput();

        //update PlayerList Table
        updateTable();

        background.update();

        //----------------------------------------------------------

        game.getBatch().begin();

        //Hintergrund zeichnen
        background.draw(game.getBatch());

        //Table Background zeichenn
        tableBackground.draw(game.getBatch());

        game.getBatch().end();

        //----------------------------------------------------------

        stage.draw();

        if(manager.getCountDownContainer() != null) {
            screenManager.setGameScreen(new Game(game, 2), game);
            screenManager.setCountdownScreen(new CountDown(game), game);
            screenManager.showScreen(screenManager.getCountdownScreen());
        }
    }

    private void updateTable() {
        if(manager.getListData() != null) {
            if(!names.equals(manager.getListData().getNames())) {
                names = manager.getListData().getNames();
                for(String s : manager.getListData().getNames()) {
//                    table.clearChildren();
                    table.add(new Label(s, new Label.LabelStyle(bitmapFont, new Color(0x555555ff)))).width(500);
                    table.row();
                }
            }
        }
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
            screenManager.showScreen(new Menu(game));
        }
    }

    @Override
    public void dispose() {

    }
}
