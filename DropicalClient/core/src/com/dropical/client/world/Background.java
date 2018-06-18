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
    private float beachElapsedTime;

    public Background() {
        background = new Sprite(new Texture(Gdx.files.internal("GUI/background.png")));
        background.setPosition(0, 0);
        background.setSize(1280, 720);

        //Beach
//        createBeachAnimation();
    }

    private void createBeachAnimation() {
        beachTexture = new Texture("GUI/background_beachAni.png");

        TextureRegion[][] connectionTmpFrames = TextureRegion.split(beachTexture, 208, 5);
        beachAnimationFrames = new TextureRegion[4];
        int connectionIndex = 0;
        //Connection Animation vorwärts in Array speichern
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 1; j++) {
                beachAnimationFrames[connectionIndex++] = connectionTmpFrames[i][j];
            }
        }
        beachAnimation = new DropicalAnimation<TextureRegion>(1f/8f, beachAnimationFrames);
        beachAnimation.setScaling(4);
        beachAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void draw(Batch batch) {
        background.draw(batch);

//        renderBeachAnimation(batch);
    }

    private void renderBeachAnimation(Batch batch) {
        beachElapsedTime += Gdx.graphics.getDeltaTime();
        beachAnimation.draw(beachElapsedTime, batch, 224, 408);
    }

}
