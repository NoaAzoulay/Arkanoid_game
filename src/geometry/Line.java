package geometry;

import java.util.List;

/**
 * @author Noa Azoulay.
 */
public class Line {

    private Point start;
    private Point end;
    /**
     * Construct a line given start point and end point.
     * @param  start  starting point of the line
     * @param  end    ending point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct a line given 2 x's and 2 y's. Makes a new point for start and another for end.
     * @param  x1  starting x point of the line
     * @param  y1  starting y point of the line
     * @param  x2  ending x point of the line
     * @param  y2  ending y point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the middle point of the line.
     * @return  primitives.Point(x, y)  average between x's and y's as x and y for the new point
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     * @return  start  end point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     * @return  end  end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if lines intersect using the intersectionWith method.
     * @param   other  other line
     * @return  true   if lines intersect
     *          false  otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * If lines intersects, finds the intersection point.
     * @param   other        other line
     * @return  primitives.Point(x, y)  point of intersection
     *          null         if lines are parallel
     */
    public Point intersectionWith(Line other) {
        double denominator = (other.end.getY() - other.start.getY()) * (this.end.getX() - this.start.getX())
                - (other.end.getX() - other.start.getX()) * (this.end.getY() - this.start.getY());
        if (denominator == 0) {
            return null;
        }
        double a = this.start.getY() - other.start.getY();
        double b = this.start.getX() - other.start.getX();
        double numerator1 = (other.end.getX() - other.start.getX()) * a - (other.end.getY() - other.start.getY()) * b;
        double numerator2 = (this.end.getX() - this.start.getX()) * a - (this.end.getY() - this.start.getY()) * b;
        a = numerator1 / denominator;
        b = numerator2 / denominator;
        double x = this.start.getX() + (a * (this.end.getX() - this.start.getX()));
        double y = this.start.getY() + (a * (this.end.getY() - this.start.getY()));
        if (a >= 0 && a <= 1 && b >= 0 && b <= 1) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * Returns the closest intersection point to the start of the line.
     * @param   rect   rectangle
     * @return  point  closest intersection point if exists
     *          null   if no intersection point exists
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List intersectionList = rect.intersectionPoints(this);
        if (intersectionList.isEmpty()) {
            return null;
        } else {
            Point intersectionPoint = (Point) rect.intersectionPoints(this).get(0);
            double minimumDistance  = this.start().distance((Point) intersectionList.get(0));
            double distance;
            for (Object anIntersectionList : intersectionList) {
                distance = this.start().distance((Point) anIntersectionList);
                if (minimumDistance > distance) {
                    minimumDistance = distance;
                    intersectionPoint = (Point) anIntersectionList;
                }
            }
            if (intersectionPoint != null) {
                return intersectionPoint;
            } else {
                return null;
            }
        }
    }

}