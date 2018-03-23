package com.pezcraft.dropical.cam;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** A camera with perspective projection and a viewport that keeps the aspect ratio by scaling the world up to take the whole screen.
 *
 * @author Pezcraft */
public class DropicalCam {
    /** {@link FillViewport} of camera **/
    private Viewport view;

    /** Constructs a new {@link FillViewport}-Camera with the given viewport size.
     *
     * @param width viewport width of camera
     * @param height viewport height of camera*/
    public DropicalCam(int width, int height) {
        view = new FillViewport(width, height, new PerspectiveCamera());
    }

    /** Resizes the camera viewport. Should be called in the resize-method.
     *
     * @param width width of the resized window
     * @param height height of the resized window
     */
    public final void update(int width, int height) {
        view.update(width, height);
    }
}
