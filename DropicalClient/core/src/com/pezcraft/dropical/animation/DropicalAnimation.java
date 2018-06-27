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
    private boolean isCenteredX = false;
    private boolean isCenteredY = false;

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

    /** Sets the alignment of X axis
     */
    public void setCenteredX(boolean centeredX) {
        isCenteredX = centeredX;
    }

    /** Sets the alignment of Y axis
     */
    public void setCenteredY(boolean centeredY) {
        isCenteredY = centeredY;
    }

    /** Draws the scaled frames.
     */
    public void draw(float stateTime, Batch batch, float x, float y) {
        TextureRegion region = (TextureRegion) getKeyFrame(stateTime);

        if(isCenteredX && !isCenteredY) {
            batch.draw(region, x-(region.getRegionWidth()*scaleX-region.getRegionWidth())/2, y, region.getRegionWidth()*scaleX, region.getRegionHeight()*scaleY);
        }
        else if(isCenteredY && !isCenteredX) {
            batch.draw(region, x, y-(region.getRegionHeight()*scaleY-region.getRegionHeight())/2, region.getRegionWidth()*scaleX, region.getRegionHeight()*scaleY);
        }
        else if(isCenteredX && isCenteredY) {
            batch.draw(region, x-(region.getRegionWidth()*scaleX-region.getRegionWidth())/2, y-(region.getRegionHeight()*scaleY-region.getRegionHeight())/2, region.getRegionWidth()*scaleX, region.getRegionHeight()*scaleY);
        }
        else {
            batch.draw(region, x, y, region.getRegionWidth()*scaleX, region.getRegionHeight()*scaleY);
        }
    }

}
