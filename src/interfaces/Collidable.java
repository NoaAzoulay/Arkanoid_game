package interfaces;

import game.Velocity;
import geometry.Point;
import geometry.Rectangle;
import objects.Ball;


/**
 * interface interfaces.Collidable.
 *
 * @author Noa Azoulay
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return  rectangle  collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param  hitter           the hitting ball
     * @param  collisionPoint   the collision point of the objects.
     * @param  currentVelocity  the current velocity of the object
     * @return  velocity        new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}


