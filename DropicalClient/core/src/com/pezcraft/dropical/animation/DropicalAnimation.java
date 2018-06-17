package com.pezcraft.dropical.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/** A scalable {@link Animation}
 *
 * @author Pezcraft */
public class DropicalAnimation<T> extends Animation {
    private float scaleX;
    private float scaleY;

    /** Constructs a new <b>scalable</b> {@link Animation}.
     *
     * @param frameDuration the time between frames in seconds.
     * @param keyFrames the objects representing the frames.
     */
    public DropicalAnimation(float frameDuration, T... keyFrames){
        super(frameDuration, keyFrames);
        scaleX=1;
        scaleY=1;
    }

    /** Scales the frames of the animation.
     */
    public void setScaling(float scale){
        scaleX = scale;
        scaleY = scale;
    }

    /** Draws the scaled frames.
     */
    public void draw(float stateTime, Batch batch, float x, float y) {
        TextureRegion region = (TextureRegion) getKeyFrame(stateTime);
        batch.draw(region, x, y, region.getRegionWidth()*scaleX, region.getRegionHeight()*scaleY);
    }

}
