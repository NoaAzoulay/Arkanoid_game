package geometry;

/**
 * class geometry.Point.
 *
 * @author Noa Azoulay.
 */
public class Point {

    private double x;
    private double y;

    /**
     * Constructs a point given x and y coordinates.
     * @param  x  horizontal value of point
     * @param  y  vertical value of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * LevelSet given x as new x.
     * @param  newX  new desired x-axis position
     */
    public void setX(double newX) {
        this.x = newX;
    }


    /**
     * Finds the distance between two points.
     * @param   other     other point
     * @return  distance  distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }


    /**
     * Returns the x-axis position of the point.
     * @return  x  x-axis position of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-axis position of the point.
     * @return  y  y-axis position of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Checks if point is on primitives.Line.
     * @param   line   line to check if the point is on it
     * @return  true   if point is on line
     *          false  otherwise
     */
    public boolean isOnLine(Line line) {
        return line.start().distance(new Point(this.x, this.y)) + line.end().distance(new Point(this.x, this.y))
                == line.start().distance(line.end());
    }

}


