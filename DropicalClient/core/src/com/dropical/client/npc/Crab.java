package com.dropical.client.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pezcraft.dropical.animation.DropicalAnimation;

public class Crab extends Actor {
    private Texture crabTexture;
    private TextureRegion[] crabAnimationFrames;
    private DropicalAnimation crabAnimation;
    private float crabElapsedTime = 0;

    public void setScaling(float scale) {
        crabAnimation.setScaling(scale);
    }
    public void setCenteredX(boolean centeredX) {
        crabAnimation.setCenteredX(centeredX);
    }
    public void setCenteredY(boolean centeredY) {
        crabAnimation.setCenteredY(centeredY);
    }

    //----------------------------------------

    public Crab(float x, float y) {
        setPosition(x, y);
        setBounds(getX(), getY(), 64, 80);
        createCrabAnimation();
    }
    private void createCrabAnimation() {
        crabTexture = new Texture("NPC/Crab/crabSettingsAni.png");

        TextureRegion[][] beachTmpFrames = TextureRegion.split(crabTexture, 64, 80);
        crabAnimationFrames = new TextureRegion[13];
        int index = 0;
        //Animation vorwärts in Array speichern
        for(int i = 0; i < 1; i++) {
            for(int j = 0; j < 13; j++) {
                crabAnimationFrames[index++] = beachTmpFrames[i][j];
            }
        }
        crabAnimation = new DropicalAnimation<TextureRegion>(1f/8f, crabAnimationFrames);
        crabAnimation.setScaling(1);
        crabAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    //----------------------------------------

    public void draw(Batch batch) {
        crabElapsedTime += Gdx.graphics.getDeltaTime();
        crabAnimation.draw(crabElapsedTime, batch, getX(), getY());
    }

}
