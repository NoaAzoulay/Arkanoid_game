package interfaces;

import biuoop.DrawSurface;

/**
 * interface interfaces.Sprite.
 *
 * @author Noa Azoulay
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param  d  surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     * @param  dt  1/fps
     */
    void timePassed(double dt);

}
