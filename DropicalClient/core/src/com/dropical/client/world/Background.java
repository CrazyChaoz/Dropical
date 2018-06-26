package com.dropical.client.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dropical.client.npc.Crab;
import com.dropical.client.npc.Fish;
import com.dropical.client.screens.Settings;
import com.pezcraft.dropical.animation.DropicalAnimation;

import java.util.ArrayList;

public class Background {
    //Singleton Stuff
    private static Background ourInstance = new Background(10);
    public static Background getInstance() {
        return ourInstance;
    }

    //Hintergrundbild
    private Sprite background;

    //Beach
    private Texture beachTexture;
    private TextureRegion[] beachAnimationFrames;
    private DropicalAnimation beachAnimation;
    private float beachElapsedTime = 0;

    //Starfish
    private Texture starfishTexture;
    private TextureRegion[] starfishAnimationFrames;
    private DropicalAnimation starfishAnimation;
    private float starfishElapsedTime = 0;

    //World for NPCs
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;
    private World world;

    //NPCs
    private ArrayList<Fish> fishList = new ArrayList<Fish>();
    private int fishAmount = 0;

    public Background(int fishAmount) {
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        this.fishAmount = fishAmount;
        createBeachAnimation();
        createStarFishAnimaton();
        createFishWorld();
        createFish();
    }

    //----------------------------------------

    private void createStarFishAnimaton() {
        starfishTexture = new Texture("NPC/Starfish/starfish.png");

        TextureRegion[][] starfishTmpFrames = TextureRegion.split(starfishTexture, 31, 18);
        starfishAnimationFrames = new TextureRegion[2];
        int index = 0;
        //Animation vorwärts in Array speichern
        for(int i = 0; i < 1; i++) {
            for(int j = 0; j < 2; j++) {
                starfishAnimationFrames[index++] = starfishTmpFrames[i][j];
            }
        }
        starfishAnimation = new DropicalAnimation<TextureRegion>(1f/8f, starfishAnimationFrames);
        starfishAnimation.setScaling(2);
        starfishAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }
    private void createBeachAnimation() {
        beachTexture = new Texture("GUI/background_beachAni.png");

        TextureRegion[][] beachTmpFrames = TextureRegion.split(beachTexture, 208, 5);
        beachAnimationFrames = new TextureRegion[13];
        int index = 0;
        //Animation vorwärts in Array speichern
        for(int i = 0; i < 13; i++) {
            for(int j = 0; j < 1; j++) {
                beachAnimationFrames[index++] = beachTmpFrames[i][j];
            }
        }
        beachAnimation = new DropicalAnimation<TextureRegion>(1f/8f, beachAnimationFrames);
        beachAnimation.setScaling(4);
        beachAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }
    private void createFishWorld() {
        world = new World(new Vector2(0, 0), true);

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, 1280/10, 720/10);
        box2DCamera.position.set(1280/2f, 720/2f, 0);
//        debugRenderer = new Box2DDebugRenderer();
    }
    private void createFish() {
        //spawn fish on random positions
        for(int i = 0; i < fishAmount; i++) {
            int randomX = (int) Math.floor(Math.random()*1000+100);
            int randomY = (int) Math.floor(Math.random()*300+10);
            fishList.add(new Fish(world, "NPC/Fish/fish.png", randomX, randomY));
        }
    }

    //----------------------------------------

    public void draw(Batch batch) {
        background.draw(batch);
        renderBeachAnimation(batch);
        renderStarfishAnimation(batch);
        renderFish(batch);
    }
    private void renderBeachAnimation(Batch batch) {
        beachElapsedTime += Gdx.graphics.getDeltaTime();
        beachAnimation.draw(beachElapsedTime, batch, 224, 408);
    }
    private void renderStarfishAnimation(Batch batch) {
        starfishElapsedTime += Gdx.graphics.getDeltaTime();

        //nachdem geblinselt, darf die Animation nicht fortgesetzt werden, deshalb FrameDuration erhöhen
        if(starfishAnimation.isAnimationFinished(starfishElapsedTime)) {
            starfishAnimation.setFrameDuration(5f);
        }
        //alle 5 Sekunden blinseln
        if(starfishElapsedTime > 5) {
            starfishAnimation.setFrameDuration(1f/18);
            starfishElapsedTime = 0;
        }

        starfishAnimation.draw(starfishElapsedTime, batch, 280, 320);
    }
    private void renderFish(Batch batch) {
        for(int i = 0; i < fishAmount; i++) {
            fishList.get(i).draw(batch);
        }
    }

    public void update() {
        //Mobs zeichnen
        for(int i = 0; i < fishAmount; i++) {
            fishList.get(i).update();
        }

//        debugRenderer.render(world, box2DCamera.combined);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

}
