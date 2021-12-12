package game;

import geometry.Line;
import geometry.Point;

import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * class Game Environment.
 *
 * @author Noa Azoulay
 */
public class GameEnvironment {

    private List<Collidable> collidables = new ArrayList<>();

    /**
     * Adds the given collidable to the environment.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Removes the given collidable to the environment.
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> pointsList = new ArrayList<Point>();
        // links between points and objects
        List<Collidable> collidablesList = new ArrayList<Collidable>();
        List<Collidable> collidablesHelper = new ArrayList<Collidable>(this.collidables);

        Point collisionPoint, suspectedPoint;
        Collidable collisionCollidable;
        Point temp;
        for (Collidable aCollidablesHelper : collidablesHelper) {
            temp = trajectory.closestIntersectionToStartOfLine(aCollidablesHelper.getCollisionRectangle());
            if (temp != null) {
                pointsList.add(temp);
                collidablesList.add(aCollidablesHelper);
            }
        }
        if (pointsList.isEmpty()) {
            return null;
        } else {
            collisionPoint = pointsList.get(0);
            collisionCollidable = collidablesList.get(0);
            for (int i = 0; i < pointsList.size(); i++) {
                suspectedPoint = pointsList.get(i);
                if (suspectedPoint.distance(trajectory.start()) < collisionPoint.distance(trajectory.start())) {
                    collisionPoint = suspectedPoint;
                    collisionCollidable = collidablesList.get(i);
                }
            }
            return new CollisionInfo(collisionPoint, collisionCollidable);
        }
    }

}
