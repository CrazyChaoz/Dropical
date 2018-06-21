package com.dropical.client.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pezcraft.dropical.animation.DropicalAnimation;

public class Background {
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

    public Background() {
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        createBeachAnimation();
        createStarFishAnimaton();
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
        beachAnimationFrames = new TextureRegion[4];
        int index = 0;
        //Animation vorwärts in Array speichern
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 1; j++) {
                beachAnimationFrames[index++] = beachTmpFrames[i][j];
            }
        }
        beachAnimation = new DropicalAnimation<TextureRegion>(1f/8f, beachAnimationFrames);
        beachAnimation.setScaling(4);
        beachAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    //----------------------------------------

    public void draw(Batch batch) {
        background.draw(batch);
        renderBeachAnimation(batch);
        renderStarfishAnimation(batch);
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

}
