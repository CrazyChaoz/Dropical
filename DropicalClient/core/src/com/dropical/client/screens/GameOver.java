package com.dropical.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.dropical.client.client.DropicalMain;
import com.dropical.client.managers.ScreenManager;
import com.pezcraft.dropical.cam.DropicalCam;

import java.util.ArrayList;
import java.util.List;

public class GameOver implements Screen {
    private DropicalCam cam;
    private Sprite background;
    private Sprite overlay;
    private BitmapFont bitmapFont;
    private List<Texture> texturList = new ArrayList<Texture>();

    //Manager
    ScreenManager screenManager = ScreenManager.getInstance();

    //Spieldaten
    private int anzahlSpieler;
    private int playerLost; //Spieler, der verloren hat
    private int pointsP1;
    private int pointsP2;

    //Hai FlyIn Animation
    private Texture sharkFlyIn;
    private TextureRegion[] sharkFlyInAnimationFrames;
    private Animation sharkFlyInAnimation;
    private float sharkFlyInElapsedTime;

    //Hai Animation Player 1
    private Texture sharkP1;
    private TextureRegion[] sharkAnimationFramesP1;
    private Animation sharkAnimationP1;
    private float sharkElapsedTimeP1;

    //Hai Animation Player 2
    private Texture sharkP2;
    private TextureRegion[] sharkAnimationFramesP2;
    private Animation sharkAnimationP2;
    private float sharkElapsedTimeP2;

    //Arena-Animationen
    private int[][][] wonArray = {
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,6,0,6,0,0,0,0},
                    {0,0,0,0,6,0,0,0,0,0},
                    {0,0,0,6,0,6,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,6,0,0,0,6,0,0,0},
                    {0,0,0,6,0,6,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,6,0,6,0,0,0,0},
                    {0,0,6,0,0,0,6,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,6,0,0,0,0,0,6,0,0},
                    {0,0,6,0,0,0,6,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,6,0,0,0,6,0,0,0},
                    {0,6,0,0,0,0,0,6,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {6,0,0,0,0,0,0,0,6,0},
                    {0,6,0,0,0,0,0,6,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,6,0,0,0,0,0,6,0,0},
                    {6,0,0,0,0,0,0,0,6,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,6},
                    {6,0,0,0,0,0,0,0,6,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {6,0,0,0,0,0,0,0,6,0},
                    {0,0,0,0,0,0,0,0,0,6},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,6},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,6},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            }
    };

    private int[][][] lostArray = {
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,1,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,0,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,1,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,0,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,1,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,0,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,1,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,0,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,1,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,0,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,1,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,0,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,1,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            },
            {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,5,5,0,0,0,0},
                    {0,0,5,5,0,0,5,5,0,0},
                    {0,5,0,0,0,0,0,0,5,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0}
            }
    };
    private long wonAnimationStartTime;
    private int wonAnimationCounter = 0;
    private long lostAnimationStartTime;
    private int lostAnimationCounter = 0;
    private long animationInterval = 200000000;

    private DropicalMain game;
    public GameOver(DropicalMain game, int anzahlSpieler, int playerLost, int pointsP1, int pointsP2) {
        this.game = game;
        this.anzahlSpieler = anzahlSpieler;
        this.playerLost = playerLost;
        this.pointsP1 = pointsP1;
        this.pointsP2 = pointsP2;
    }

    @Override
    public void show() {
        //Hai-Animation zurücksetzen
        sharkFlyInElapsedTime = 0;
        sharkElapsedTimeP1 = 0;
        sharkElapsedTimeP2 = 0;

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

        //Overlay (je nachdem welcher Spieler verloren hat)
        overlay = new Sprite(new Texture(Gdx.files.internal("GUI/overlay/game_over_player" + playerLost + ".png")));
        overlay.setPosition(0, 0);
        overlay.setSize(1280, 720);

        //Schrift für Steuerung-Erklärung
        bitmapFont = new BitmapFont(Gdx.files.internal("BitmapFont/TetrisFont.fnt"));
        bitmapFont.getData().setScale(0.9f);
        bitmapFont.setColor(new Color(0x4C4C4Cff));

        //Hai Animation (Game over)
        sharkFlyIn = new Texture("Shark/sharkFlyInAnimation.png");
        sharkP1 = new Texture("Shark/sharkAnimation.png");
        sharkP2 = new Texture("Shark/sharkAnimation flipX.png");

        TextureRegion[][] sharkFlyInTmpFrames = TextureRegion.split(sharkFlyIn, 256, 256);
        TextureRegion[][] sharkTmpFramesP1 = TextureRegion.split(sharkP1, 256, 256);
        TextureRegion[][] sharkTmpFramesP2 = TextureRegion.split(sharkP2, 256, 256);
        sharkFlyInAnimationFrames = new TextureRegion[6];
        sharkAnimationFramesP1 = new TextureRegion[12];
        sharkAnimationFramesP2 = new TextureRegion[12];
        int sharkFlyInIndex = 0;
        int sharkIndexP1 = 0;
        int sharkIndexP2 = 0;
        //Shark FlyIn Animation nur vorwärts in Array speichern (kein Loop)
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                sharkFlyInAnimationFrames[sharkFlyInIndex++] = sharkFlyInTmpFrames[i][j];
            }
        }
        //Shark Animation rückwärts in Array speichern (für den Loop)
        for(int i = 1; i >= 0; i--) {
            for(int j = 2; j >= 0; j--) {
                sharkAnimationFramesP1[sharkIndexP1++] = sharkTmpFramesP1[i][j];
                sharkAnimationFramesP2[sharkIndexP2++] = sharkTmpFramesP2[i][j];
            }
        }
        //dann Shark Animation vorwärts in Array hinten anfügen (für den Loop)
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                sharkAnimationFramesP1[sharkIndexP1++] = sharkTmpFramesP1[i][j];
                sharkAnimationFramesP2[sharkIndexP2++] = sharkTmpFramesP2[i][j];
            }
        }
        sharkFlyInAnimation = new Animation<TextureRegion>(1f/36f, sharkFlyInAnimationFrames);
        sharkAnimationP1 = new Animation<TextureRegion>(1f/36f, sharkAnimationFramesP1);
        sharkAnimationP2 = new Animation<TextureRegion>(1f/36f, sharkAnimationFramesP2);

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

        //Eingabeerklärung zeichnen
        bitmapFont.draw(game.getBatch(), "Menu [ESC]", 0, 350, 1280, 1, false);

        //Punkte zeichnen
        bitmapFont.draw(game.getBatch(), Long.toString(pointsP1), 376, 133, 172, 1, false);
        if(anzahlSpieler == 2) {
            bitmapFont.draw(game.getBatch(), Long.toString(pointsP2), 732, 133, 172, 1, false);
        }

        renderSharkAnimation();

        renderLostAnimation();
        if(anzahlSpieler == 2) {
            renderWonAnimation();
        }

        game.getBatch().end();
    }
    private void renderSharkAnimation() {
        //Hai-Animation zeichnen
        if(playerLost == 1) {
            sharkFlyInElapsedTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentSharkFlyInFrame = (TextureRegion) sharkFlyInAnimation.getKeyFrame(sharkFlyInElapsedTime,false);
            if(!sharkFlyInAnimation.isAnimationFinished(sharkFlyInElapsedTime)) {
                game.getBatch().draw(currentSharkFlyInFrame, 0, 350);
            }

            sharkElapsedTimeP1 += Gdx.graphics.getDeltaTime();
            TextureRegion currentSharkFrame = (TextureRegion) sharkAnimationP1.getKeyFrame(sharkElapsedTimeP1,true);
            if(sharkFlyInAnimation.isAnimationFinished(sharkFlyInElapsedTime)) {
                game.getBatch().draw(currentSharkFrame, 0, 350);
            }
        }
        if(playerLost == 2) {
            sharkFlyInElapsedTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentSharkFlyInFrame = (TextureRegion) sharkFlyInAnimation.getKeyFrame(sharkFlyInElapsedTime,false);
            if(!currentSharkFlyInFrame.isFlipX()) {
                currentSharkFlyInFrame.flip(true, false);		//spiegeln
            }
            if(!sharkFlyInAnimation.isAnimationFinished(sharkFlyInElapsedTime)) {
                game.getBatch().draw(currentSharkFlyInFrame, 1024, 350);
            }

            sharkElapsedTimeP2 += Gdx.graphics.getDeltaTime();
            TextureRegion currentSharkFrame = (TextureRegion) sharkAnimationP2.getKeyFrame(sharkElapsedTimeP2,true);
            if(sharkFlyInAnimation.isAnimationFinished(sharkFlyInElapsedTime)) {
                game.getBatch().draw(currentSharkFrame, 1024, 350);
            }
        }
    }
    private void renderWonAnimation() {
        //Gewinner-Arena zeichnen
        for(int y = 0; y < 20; y++) {
            for(int x = 0; x < 10; x++) {
                //leere Tetrominos (0er) nicht rendern
                if(wonArray[wonAnimationCounter][y][x] != 0) {
                    if(playerLost == 1) {
                        game.getBatch().draw(texturList.get(wonArray[wonAnimationCounter][y][x]), (float) x*20+840, (float) 19*20-y*20+280);
                    }
                    else {
                        game.getBatch().draw(texturList.get(wonArray[wonAnimationCounter][y][x]), (float) x*20+240, (float) 19*20-y*20+280);
                    }
                }
            }
        }

        //Gewinner-Animation Countdown updaten
        if(TimeUtils.timeSinceNanos(wonAnimationStartTime) > animationInterval) {
            if(wonAnimationCounter == 13) {
                wonAnimationCounter = 0;
            }
            else {
                wonAnimationCounter++;
            }

            wonAnimationStartTime = TimeUtils.nanoTime();
        }
    }
    private void renderLostAnimation() {
        //Verlierer-Arena zeichnen
        for(int y = 0; y < 20; y++) {
            for(int x = 0; x < 10; x++) {
                //leere Tetrominos (0er) nicht rendern
                if(lostArray[lostAnimationCounter][y][x] != 0) {  //leere Tetrominos (0er) nicht rendern
                    if(playerLost == 1) {
                        game.getBatch().draw(texturList.get(lostArray[lostAnimationCounter][y][x]), (float) x*20+240, (float) 19*20-y*20+280);
                    }
                    else {
                        game.getBatch().draw(texturList.get(lostArray[lostAnimationCounter][y][x]), (float) x*20+840, (float) 19*20-y*20+280);
                    }
                }
            }
        }

        //Verlierer-Animation Countdown updaten
        if(TimeUtils.timeSinceNanos(lostAnimationStartTime) > animationInterval) {
            if(lostAnimationCounter == 7) {
                lostAnimationCounter = 0;
            }
            else {
                lostAnimationCounter++;
            }

            lostAnimationStartTime = TimeUtils.nanoTime();
        }
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
            screenManager.showScreen(new Menu(game));
        }
    }

    @Override
    public void dispose() {

    }
}
