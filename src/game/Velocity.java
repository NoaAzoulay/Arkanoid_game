package game;

import geometry.Point;

/**
 * class game.Velocity.
 *
 * @author Noa Azoulay.
 */
public class Velocity {


    private double dx;
    private double dy;

    /**
     * Constructs the velocity of the ball by x-axis velocity and y-axis velocity.
     *
     * @param dx change in the x axis
     * @param dy change in the y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs the velocity of the ball by angle and speed.
     *
     * @param angle moving angle of ball
     * @param speed moving speed of ball
     * @return new primitives.Velocity  new velocity from angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }


    /**
     * Returns the x-axis velocity.
     *
     * @return dx  this dx
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * Returns the y-axis velocity.
     *
     * @return dy  this dy
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Applies change in the axes for movement.
     *
     * @param p  point to move
     * @param dt 1/fps
     * @return Point  point with new position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);

    }

}