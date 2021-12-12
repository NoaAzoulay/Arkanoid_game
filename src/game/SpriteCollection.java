package game;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * class game.SpriteCollection.
 *
 * @author Noa Azoulay.
 */
public class SpriteCollection {

    private List<Sprite> sprites = new ArrayList<>();

    /**
     * Adds sprite to the list.
     * @param  s  sprite to add to the list.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes sprite from the list.
     * @param  s  sprite to remove from the list.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Calls timePassed() on all sprites.
     * @param  dt  1/fps
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> spritesCollection = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : spritesCollection) {
            sprite.timePassed(dt);
        }
    }

    /**
     * Calls DrawOn(d) on all sprites.
     * @param  d  the surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCollection = new ArrayList<Sprite>(this.sprites);
        for (Sprite aSpritesCollection : spritesCollection) {
            aSpritesCollection.drawOn(d);
        }
    }


}
