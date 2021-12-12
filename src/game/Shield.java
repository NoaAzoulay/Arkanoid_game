package game;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import objects.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Shield.
 * @author Noa Azoulay
 */
public class Shield implements Collidable, Sprite, HitNotifier {

    private Rectangle shield;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor for Shield.
     * @param  p  upper-left corner point
     */
    public Shield(Point p) {
        this.shield = new Rectangle(p, 4, 4);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.shield.getUpperLeft(), this.shield.getWidth(), this.shield.getHeight());
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        hitter.notifyHit(this);
        return currentVelocity;
    }

    /**
     * Notify all subscribers about hits.
     * @param  hitter  hitting ball
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener listener : listeners) {
            listener.hitEvent(this, hitter);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle((int) this.shield.getX(), (int) this.shield.getY(), 4, 4);
    }

    @Override
    public void timePassed(double dt) {
    }

    /**
     * Adds the block to the game as sprites.Sprite and as Collidable.
     * @param  g  game to add the block
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove the block from the game as sprites.Sprite and as Collidable.
     * @param  g  game to remove the block
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

}
