package com.pezcraft.dropical.cam;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** A camera with orthographic projection and a viewport that keeps the aspect ratio by scaling the world up to take the whole screen.
 * <br>
 * Don' t forget to update the cam in the resize method.
 * <h3>Usage Example</h3>
 * <pre>{@code
 DropicalCam cam;

 public void show() {    //show or. create
    cam = new DropicalCam(1280, 720);

    //only if you have a stage for something other
    stage.setViewport(dropicalCam.getViewport());
 }

 public void resize(int width, int height) {
    dropicalCam.update(width, height);

    //only if you have a stage for something other
    stage.getViewport().update(width, height);
 }}</pre>
 *
 * @author Pezcraft */
public class DropicalCam {
    /** {@link FillViewport} of camera **/
    private Viewport view;



    /** Constructs a new {@link FillViewport}-Camera with the given viewport size.
     *
     * @param width viewport width of camera
     * @param height viewport height of camera
     *
     */
    public DropicalCam(int width, int height) {
        view = new FillViewport(width, height, new OrthographicCamera());
    }



    /** Resizes the camera viewport. Should be called in the resize-method.
     *  <br>
     *  If you use a stage, add the viewport of the {@link DropicalCam} to the stage and update the viewport then.
     *  <pre>{@code
    public void show() {    //show or. create
        stage.setViewport(dropicalCam.getViewport());
    }

    public void resize(int width, int height) {
        dropicalCam.update(width, height);
        stage.getViewport().update(width, height);
    }}</pre>
     * @param width width of the resized window
     * @param height height of the resized window
     */
    public final void update(int width, int height) {
        view.update(width, height, true);
    }



    /**
     * @return the viewport of the camera.
     */
    public Viewport getViewport() {
        return view;
    }
}
