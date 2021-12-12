package objects;

import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameEnvironment;
import game.Shield;
import game.Velocity;
import game.Alien;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


/**
 * class objects.Ball:center, location, radius, color and velocity.
 *
 * @author Noa Azoulay
 */
public class Ball implements Sprite, HitNotifier {


    private Image playerShot;
    private Image enemyShot;
    private Point center;
    private String color;
    private Velocity velocity;
    private GameEnvironment environment;
    private List<HitListener> hitListeners;

    /**
     * Constructs a ball by center point.
     * @param  center       center point of ball
     * @param  s            string for display
     * @param  environment  game environment for the ball
     */
    public Ball(Point center, String s, GameEnvironment environment) {
        this.center         = center;
        this.color          = s;
        this.environment    = environment;
        this.hitListeners   = new ArrayList<>();
        try {
            this.enemyShot = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "enemyshot.png"));
            this.playerShot = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "playershot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns color.
     * @return  this.color  string.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Returns the center point of the ball.
     * @return  center  center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Draws the ball on given surface using (x and y coordinates, and radius) parameters.
     * @param  d  drawing surface
     */
    public void drawOn(DrawSurface d) {
        if (this.color.equals("p")) {
            d.drawImage((int) this.center.getX() - 5, (int) this.center.getY() - 5, this.playerShot);
        } else {
            d.drawImage((int) this.center.getX() - 5, (int) this.center.getY() - 5, this.enemyShot);
        }
    }

    /**
     * LevelSet the velocity of the ball as the given parameter.
     * @param  v  velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * LevelSet the velocity of the ball as the given parameters.
     * @param  angle  moving direction
     * @param  speed  moving speed
     */
    public void setVelocity(double angle, double speed) {
        this.velocity = Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Returns the velocity of the ball.
     * @return  velocity  velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step. Changes direction if hit wall.
     * @param  dt  1/fps
     */
    public void moveOneStep(double dt) {
        Point collisionPoint;
        Collidable collisionObject;
        CollisionInfo collision = this.environment.getClosestCollision(this.getTrajectory(dt));
        if (collision != null) {
            collisionPoint = collision.collisionPoint();
            collisionObject = collision.collisionObject();
            this.velocity = collisionObject.hit(this, collisionPoint, this.velocity);
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        } else {
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        }
    }

    /**
     * Notify all subscribers about hits.
     * @param  hitted  hitted block
     */
    public void notifyHit(Shield hitted) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener listener : listeners) {
            listener.hitEvent(hitted, this);
        }
    }
    /**
     * Notify all subscribers about hits.
     * @param  hitted  hitted block
     */
    public void notifyHit(Alien hitted) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener listener : listeners) {
            listener.hitEvent(hitted, this);
        }
    }
    /**
     * Notify all subscribers about hits.
     * @param  hitted  hitted block
     */
    public void notifyHit(Paddle hitted) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener listener : listeners) {
            listener.hitEvent(hitted, this);
        }
    }

    /**
     * Returns the balls movement path.
     * @param   dt    1/fps
     * @return  Line  path of movement
     */
    public Line getTrajectory(double dt) {
        return new Line(this.getCenter(), new Point(this.getCenter().getX() + this.getVelocity().getDX() * dt,
                this.getCenter().getY() + this.getVelocity().getDY() * dt));
    }

    /**
     * Moves one step for time.
     * @param  dt  1/fps
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * Adds the ball to the environment as a sprites.Sprite.
     * @param  g  active environment to add the ball
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removes the ball from the environment as a sprites.Sprite.
     * @param  g  active environment to remove the ball
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     * @param  hl  listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param  hl  listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}