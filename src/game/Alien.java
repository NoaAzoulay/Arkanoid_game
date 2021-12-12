package game;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import objects.Ball;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Alien.
 * @author Noa Azoulay
 */
public class Alien implements Collidable, Sprite, HitNotifier {

    private Rectangle alien;
    private final double startY, startX;
    private Image image;
    private List<HitListener> hitListeners  = new ArrayList<>();

    /**
     * Constructor for Alien.
     * @param  p  upper-left corner point
     */
    public Alien(Point p) {
        this.alien = new Rectangle(p, 40, 30);
        this.startX = p.getX();
        this.startY = p.getY();
        Random rand = new Random();
        try {
            this.image = ImageIO.read(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("spider" + Integer.toString(rand.nextInt(5)) + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets upper left point.
     * @param  x  new x coordinate
     * @param  y  new y coordinate
     */
    public void setUpperLeft(double x, double y) {
        Point upperLeft = new Point(x, y);
        this.alien = new Rectangle(upperLeft, 40, 30);
    }


    /**
     * Moves the block.
     * @param  dx  vertical velocity of the block
     */
    public void move(double dx) {
        double x = this.alien.getX() + dx;
        this.setUpperLeft(x, this.alien.getY());
    }

    /**
     * Returns the initial x position of block.
     * @return double  startX
     */
    public double getStartY() {
        return startY;
    }

    /**
     * Returns the initial y position of block.
     * @return  double  startY
     */
    public double getStartX() {
        return startX;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) this.alien.getX(), (int) this.alien.getY(), this.image);

    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
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
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.alien.getUpperLeft(), this.alien.getWidth(), this.alien.getHeight());
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        hitter.notifyHit(this);
        return currentVelocity;
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
    /**
     * Returns upper left point.
     * @return  Point  upper left point
     */
    public double getX() {
        return this.alien.getX();
    }

    /**
     * Returns upper left point.
     * @return  Point  upper left point
     */
    public double getY() {
        return this.alien.getY();
    }

}
