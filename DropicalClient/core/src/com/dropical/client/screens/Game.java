package com.dropical.client.screens;

import at.dropical.shared.GameState;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.requests.HandleInputRequest;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.DataManager;
import com.dropical.client.managers.ScreenManager;
import com.pezcraft.dropical.cam.DropicalCam;

import java.util.ArrayList;
import java.util.List;

public class Game implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private Sprite overlay;
    private BitmapFont bitmapFont;
    private List<Texture> texturList = new ArrayList<Texture>();

    //Manager
    private ScreenManager screenManager = ScreenManager.getInstance();

    //Server
    private DataManager manager;

    //Spieldaten
    private int anzahlSpieler;
    private int points = 0;
    private int pointsP1 = 0;
    private int pointsP2 = 0;   //pointsP1 & pointsP2 werden nur beim Aufrufen des GameOver-Screens benötigt
    private int level = 0;
    private long timeTillNextLevel = 0;
    private GameState gameState;
    private GameState gameStateP1;
    private GameState gameStateP2;

    //Tetromino-Attribute
    private int[][] arenaArray;
    private int[][] tetArray;
    private int[][] nextTetArray;
    private int tPosX;
    private int tPosY;

    //Ghost-Tetromino-Attribute
    private int[][] ghostArray;
    private int ghostPosX;
    private int ghostPosY;

    //Tastendrücke der Spieler
    private PlayerAction gameKeyP1;
    private PlayerAction gameKeyP2;

    private DropicalMain game;
    public Game(DropicalMain game, int anzahlSpieler) {
        this.game = game;
        this.anzahlSpieler = anzahlSpieler;
    }

    @Override
    public void show() {
        manager = DataManager.getInstance();

        //Schrift für Punkte, Level, ... festlegen
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Tetromino-Texturen zur ArrayList hinzufügen
        texturList.add(new Texture("Tetrominos/gray.png"));        //Index 0
        texturList.add(new Texture("Tetrominos/lightblue.png"));   //Index 1
        texturList.add(new Texture("Tetrominos/purple.png"));      //Index 2
        texturList.add(new Texture("Tetrominos/orange.png"));      //Index 3
        texturList.add(new Texture("Tetrominos/blue.png"));        //Index 4
        texturList.add(new Texture("Tetrominos/red.png"));         //Index 5
        texturList.add(new Texture("Tetrominos/green.png"));       //Index 6
        texturList.add(new Texture("Tetrominos/yellow.png"));      //Index 7
        texturList.add(new Texture("Tetrominos/ghost.png"));      //Index 8

        //Hintergrund
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        //Overlay
        overlay = new Sprite(new Texture(Gdx.files.internal("GUI/overlay/game_running.png")));
        overlay.setPosition(0, 0);
        overlay.setSize(1280, 720);

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

        //Overlay zeichnen
        overlay.draw(game.getBatch());

        //Zeit zeichnen
        bitmapFont.draw(game.getBatch(), "nxt lvl:", 0, 135, 1280, 1, false);
        bitmapFont.draw(game.getBatch(), Long.toString(timeTillNextLevel), 0, 105, 1280, 1, false);

        try {
            //auf 1. Spieler switchen und dessen PollRequest laden + rendern
            pollPlayerInfo(0);
            updatePollRequestData(0);
            gameStateP1 = gameState;
            pointsP1 = points;
            renderPlay1er1();
            checkGameLost();

            //wenn Multiplayer, dann auf 2. Spieler switchen und seinen PollRequest laden + rendern
            if(anzahlSpieler == 2) {
                pollPlayerInfo(1);
                updatePollRequestData(1);
                gameStateP2 = gameState;
                pointsP2 = points;
                renderPlay1er2();
                checkGameLost();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        game.getBatch().end();
    }
    private void renderPlay1er1() {
        //Punkte und Level zeichnen
        //376... x-Position des Statistik-Feldes
        bitmapFont.draw(game.getBatch(), Long.toString(points), 376, 158, 172, 1, false);
        bitmapFont.draw(game.getBatch(), Integer.toString(level), 376, 105, 172, 1, false);

        buildArena();
        //Arena zeichnen
        for(int y = 0; y < 20; y++) {
            for(int x = 0; x < 10; x++) {
                game.getBatch().draw(texturList.get(arenaArray[y][x]), (float) x*20+240, (float) 19*20-y*20+280);
            }
        }

        //Nächsten Tetromino in Feld zeichnen
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                game.getBatch().draw(texturList.get(nextTetArray[y][x]), (float) x*20+152, (float) 3*20-y*20+88);
            }
        }

    }
    private void renderPlay1er2() {
        //Punkte und Level zeichnen
        //732... x-Position des Statistik-Feldes
        bitmapFont.draw(game.getBatch(), Long.toString(points), 732, 158, 172, 1, false);
        bitmapFont.draw(game.getBatch(), Integer.toString(level), 732, 105, 172, 1, false);

        buildArena();
        //Arena zeichnen
        for(int y = 0; y < 20; y++) {
            for(int x = 0; x < 10; x++) {
                game.getBatch().draw(texturList.get(arenaArray[y][x]), (float) x*20+840, (float) 19*20-y*20+280);
            }
        }

        //Nächsten Tetromino in Feld zeichnen
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                game.getBatch().draw(texturList.get(nextTetArray[y][x]), (float) x*20+1048, (float) 3*20-y*20+88);
            }
        }

    }

    //wenn Gameover, dann auf GameOver-Screen wechseln
    private void checkGameLost() {
        if(gameState == GameState.GAME_LOST) {
            if(gameStateP1 == GameState.GAME_LOST) {
                screenManager.setGameOverScreen(new GameOver(game, anzahlSpieler, 1, pointsP1, pointsP2), game);
                screenManager.showScreen(screenManager.getGameOverScreen());
            }
            if(gameStateP2 == GameState.GAME_LOST) {
                screenManager.setGameOverScreen(new GameOver(game, anzahlSpieler, 2, pointsP1, pointsP2), game);
                screenManager.showScreen(screenManager.getGameOverScreen());
            }
        }
    }

    private void pollPlayerInfo(int playerNo) throws Exception {
        if(playerNo == 0) {
            handleInputP1();
            manager.getProxy().writeToServer(new HandleInputRequest("RP1", gameKeyP1));
        } else {
            handleInputP2();
            manager.getProxy().writeToServer(new HandleInputRequest("Kemps_JFX_GUI2", gameKeyP2));
        }
    }
    private void updatePollRequestData(int playerNo) {
        if(manager.getGameData() != null) {
            arenaArray = manager.getGameData().getArenas().get(playerNo);
            tetArray = manager.getGameData().getCurrTrocks().get(playerNo);
            nextTetArray = manager.getGameData().getNextTrocks().get(playerNo);
            tPosX = manager.getGameData().getCurrTrockXs().get(playerNo);
            tPosY = manager.getGameData().getCurrTrockYs().get(playerNo);
            gameState = manager.getGameData().getCurrentState();
//        points = pollRequest.getScore();
            level = manager.getGameData().getLevels().get(playerNo);
//        timeTillNextLevel = pollRequest.getTime();

//            //Ghost aus neuen Daten erstellen
//            ghostArray = tetArray;
//            ghostPosX = tPosX-2;
//            ghostPosY = tPosY-4;
//            //verhindern der IndexOutOfBounds-Exception
//            if(ghostPosY < 0) {
//                ghostPosY = 0;
//            }
        }

    }
    private void buildArena() {
        int xTMP;
        int yTMP;

        //Ghost-Tetromino in Arena schreiben
        if(gameState == GameState.RUNNING) {
            while(!moveGhostDown());
        }

        //Tetromino in Arena schreiben
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                if(tetArray[y][x] != 0) { //Bereich mit 0er ignorieren
                    xTMP = tPosX-2+x;   //-2, weil die Arena rechts und links unsichtbar ist
                    yTMP = tPosY-4+y;   //-4, weil die Arena oben unsichtbar ist
                    //sicherstellen, dass der aktuelle einzelne Block des Tetrominos im sichtbare Bereich liegt
                    if(xTMP >= 0 && yTMP >= 0 && xTMP < 10 && yTMP < 20) {
                        //Tetromino in Arena schreiben
                        arenaArray[yTMP][xTMP] = tetArray[y][x];
                    }
                }
            }
        }

    }

    //Ghost-Tetromino
    private boolean checkGhost(int[][] ghostTetArray, int x_new, int y_new) {
        //*** überprüft, ob der übergebene Tetromino bei dem angegeben Koordinaten gesetzt werden kann ***
        boolean placeable = false;

        int xTMP;
        int yTMP;

        //zählt Tetromino-Array durch
        for(int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                //geht nur hinein, wenn im aktuellen Tetromino-Array Index ein Block drinnen liegt.
                //Wenn nicht, ist der Tetromino vorerst platzierbar.
                if(ghostTetArray[y][x] > 0) {
                    yTMP = y_new+y;
                    xTMP = x_new+x;

                    //Überprüfung, wegen IndexOutOfBounds-Exception
                    if(yTMP >= 0 && yTMP < 20) {
                        //Ausgangspunkt im Arena-Array (x_new, y_new) + Position im Tetromino-Array (for-Schleifen-Durchlaufvariable x & y)
                        //Wenn an diesem Punkt kein Block vorhanden ist (0), ist der Tetromino vorerst platzierbar.
                        //ansonsten nicht platzierbar und es wird false returned
                        if(arenaArray[yTMP][xTMP] == 0) {
                            placeable = true;
                        }
                        else {
                            return false;
                        }
                    }
                    else {
                        return false;
                    }
                }
                else {
                    placeable = true;
                }
            }
        }

        return placeable;
    }
    private void setGhost(int[][] ghostTetArray, int x_new, int y_new) {
        //setzt übergebenen Ghost-Tetromino bei den angegeben Koordinaten
        if(checkGhost(ghostTetArray, x_new, y_new)) {
            for(int y = 0; y < 4; y++) {
                for(int x = 0; x < 4; x++) {
                    //nur setzen, wenn ein Block im Ghost-Tetromino-Array drinnen steht
                    if(ghostTetArray[y][x] != 0) {
                        arenaArray[y_new+y][x_new+x] = 8;
                    }
                }
            }
        }
    }
    private boolean moveGhostDown() {
        boolean moveable = false;	//Rückgabewert, ob Ghost-Tetromino ganz unten angekommen ist

        //wenn der Ghost-Tetromino nicht eine Position weiter unten platziert werden konnte -> in Arena setzen
        if(!checkGhost(ghostArray, ghostPosX, ghostPosY+1)) {
            setGhost(ghostArray, ghostPosX, ghostPosY);
            moveable = true;
        }
        else {
            ghostPosY++;
        }

        return moveable;
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
        gameKeyP1 = PlayerAction.NOKEY;
        gameKeyP2 = PlayerAction.NOKEY;

        //pausieren
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gameKeyP1 = PlayerAction.PAUSE; //wegen SinglePlayer muss der 1. Spieler pausieren
        }

        //Spiel abbrechen
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gameKeyP1 = PlayerAction.QUIT; //wegen SinglePlayer muss der 1. Spieler das Spiel abbrechen
            screenManager.setMenuScreen(new Menu(game), game);
            screenManager.showScreen(screenManager.getMenuScreen());
        }
    }
    private void handleInputP1() {
        //Tetromino-Steuerung
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            gameKeyP1 = PlayerAction.LEFT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            gameKeyP1 = PlayerAction.RIGHT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            gameKeyP1 = PlayerAction.DOWN;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            gameKeyP1 = PlayerAction.UP;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            gameKeyP1 = PlayerAction.DROP;
        }
    }
    private void handleInputP2() {
        //Tetromino-Steuerung
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gameKeyP2 = PlayerAction.LEFT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gameKeyP2 = PlayerAction.RIGHT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            gameKeyP2 = PlayerAction.DOWN;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            gameKeyP2 = PlayerAction.UP;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameKeyP2 = PlayerAction.DROP;
        }
    }

    @Override
    public void dispose() {

    }

}
