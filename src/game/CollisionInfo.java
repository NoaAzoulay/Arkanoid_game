package game;

import geometry.Point;
import interfaces.Collidable;

/**
 * class Game game.CollisionInfo.
 *
 * @author Noa Azoulay
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Constructor for Collision Information.
     * @param  p  point of collision
     * @param  c  object of collision
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collidable = c;
    }

    /**
     * Returns the point where the collision occurs.
     * @return  point  collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     * @return  collidable  object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collidable;
    }

}