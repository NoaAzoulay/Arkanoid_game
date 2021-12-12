package interfaces;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 * @author Noa Azoulay
 */
public interface Animation {

    /**
     * Draws one frame.
     * @param  d   surface to draw
     * @param  dt  1/fps
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Checks if the animation should stop.
     * @return  true   if should stop
     *          false  otherwise
     */
    boolean shouldStop();

}
