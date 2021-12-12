package objects;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import game.GameLevel;
import game.SpaceInvaders;
import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


/**
 * The type objects.Paddle.
 *
 * @author Noa Azoulay. Class objects.Paddle. members.
 */
public class Paddle implements Sprite, Collidable, HitNotifier {

    private Image image;
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private int speed;
    private long lastShot;
    private List<HitListener> hitListeners;

    /**
     * Constructs the paddle.
     * @param  x       starting x position of the paddle
     * @param  width   WIDTH of the paddle
     * @param  speed   speed of the paddle
     * @param  k       keyboard for the paddle
     */
    public Paddle(double x, double width, int speed, KeyboardSensor k) {
        this.paddle = new Rectangle(new Point(x, 550), width, 5);
        this.keyboard = k;
        this.speed = speed;
        this.lastShot = 0;
        this.hitListeners = new ArrayList<HitListener>();
        try {
            this.image = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("paddle.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Moves the paddle left.
     * @param  dt  1/fps
     */
    public void moveLeft(double dt) {
        if (this.paddle.getUpperLeft().getX() <= this.speed * dt) {
            this.paddle.setPosition(0);
            return;
        }
        this.paddle.setPosition(paddle.getUpperLeft().getX() - this.speed * dt);
    }

    /**
     * Moves the paddle right.
     * @param  dt  1/fps
     */
    public void moveRight(double dt) {
        if (this.paddle.getBottomRight().getX() >= SpaceInvaders.WIDTH - this.speed * dt) {
            this.paddle.setPosition(SpaceInvaders.WIDTH - this.getCollisionRectangle().getWidth());
            return;
        }
        this.paddle.setPosition(paddle.getUpperLeft().getX() + this.speed * dt);
    }

    @Override
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(), this.image);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.paddle.getUpperLeft(), this.paddle.getWidth(), this.paddle.getHeight());
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newSpeed = Math.sqrt((currentVelocity.getDX() * currentVelocity.getDX())
                + (currentVelocity.getDY() * currentVelocity.getDY()));
        if (collisionPoint.isOnLine(this.paddle.getTop()) && currentVelocity.getDY() >= 0) {
            for (int i = 1; i <= 15; i++) {
                if (collisionPoint.getX() < this.paddle.getUpperLeft().getX() + i * (this.paddle.getWidth() / 15)) {
                    this.notifyHit(hitter);
                    hitter.notifyHit(this);
                    return new Velocity(currentVelocity.getDX(), currentVelocity.getDY());
                }
            }
        }
        return new Velocity(currentVelocity.getDX(), currentVelocity.getDY());
    }

    /**
     * Shoots a shot.
     * @param  game  gamelevel
     */
    public void shoot(GameLevel game) {
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (System.currentTimeMillis() - lastShot > 350) {
                this.lastShot = System.currentTimeMillis();
                Ball shot = game.playerShot(new Point(
                        this.getCollisionRectangle().getTop().start().getX() + 8,
                        this.getCollisionRectangle().getTop().start().getY() - 1));
                shot.setVelocity(0, 500);
                shot.addToGame(game);
            }
        }
    }

    /**
     * Adds the paddle to the game as a sprites.Sprite and as a Collidable.
     * @param  g  active game to add the paddle.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
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


}