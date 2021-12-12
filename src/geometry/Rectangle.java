package geometry;


import java.util.ArrayList;
import java.util.List;
/**
 * class geometry.Rectangle.
 *
 * @author Noa Azoulay
 */

/**
 * rectangle is build by upperleft point, width and height.
 * each rec is build by 4 lines. to horizontal and 2 vertical.
 */
public class Rectangle {
    private Point upperLeft, upperRight, bottomLeft, bottomRight;
    private double width, height;
    private Line top, bottom, left, right;

    /**
     * Constructs a new rectangle with location and WIDTH/HEIGHT.
     * @param  upperLeft  upper-left point of the rectangle
     * @param  width      WIDTH of the rectangle
     * @param  height     HEIGHT of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.top = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        this.bottom = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.left = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        this.right = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperRight = new Point(this.top.end().getX(), this.top.end().getY());
        this.bottomLeft = new Point(this.bottom.start().getX(), this.bottom.start().getY());
        this.bottomRight = new Point(this.bottom.end().getX(), this.bottom.end().getY());
    }

    /**
     * Returns a (possibly empty) List of intersection points with specified line.
     * @param   line  a line for intersection.
     * @return  list  list of all intersection points with specified line
     */
    public List intersectionPoints(Line line) {
        Line[] rectLines = {this.top, this.bottom, this.left, this.right};
        ArrayList<Point> pointsList = new ArrayList<Point>();
        for (Line rectLine : rectLines) {
            if (line.isIntersecting(rectLine)) {
                pointsList.add(line.intersectionWith(rectLine));
            }
        }
        return pointsList;
    }

    /**
     * updates the rectangle when values are changed.
     */
    public void updateRectangle() {
        this.top = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        this.bottom = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.left = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        this.right = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.upperRight = new Point(this.top.end().getX(), this.top.end().getY());
        this.bottomLeft = new Point(this.bottom.start().getX(), this.bottom.start().getY());
        this.bottomRight = new Point(this.bottom.end().getX(), this.bottom.end().getY());
    }
    /**
     * Returns the WIDTH of the rectangle.
     * @return  WIDTH  WIDTH of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the HEIGHT of the rectangle.
     * @return  HEIGHT  WIDTH of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the bottom-right point of the rectangle.
     * @return  bottomRight  bottom-right point of the rectangle
     */
    public Point getBottomRight() {
        return new Point(this.bottomRight.getX(), this.bottomRight.getY());
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return  upperLeft  upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * Returns the bottom line of the rectangle.
     * @return  bottom  right line of the rectangle
     */
    public Line getBottom() {
        return new Line(this.bottom.start(), this.bottom.end());
    }

    /**
     * Returns the left line of the rectangle.
     * @return  left  right line of the rectangle
     */
    public Line getLeft() {
        return new Line(this.left.start(), this.left.end());
    }

    /**
     * Returns the right line of the rectangle.
     * @return  right  right line of the rectangle
     */
    public Line getRight() {
        return new Line(this.right.start(), this.right.end());
    }

    /**
     * Returns the top line of the rectangle.
     * @return  top  right line of the rectangle
     */
    public Line getTop() {
        return new Line(this.top.start(), this.top.end());
    }

    /**
     * LevelSet new position for the rectangle to move vertically.
     * @param  x  new upper-left corner x-coordinate
     */
    public void setPosition(double x) {
        this.upperLeft.setX(x);
        updateRectangle();
    }

    /**
     * Returns upper left point.
     * @return  Point  upper left point
     */
    public double getX() {
        return this.getUpperLeft().getX();
    }

    /**
     * Returns upper left point.
     * @return  Point  upper left point
     */
    public double getY() {
        return this.getUpperLeft().getY();
    }


}


