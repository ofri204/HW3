/**
 * Represents a rectangle shape with width and height.
 * Inherits from the abstract Shape class and implementations it's methods.
 **/
public class Rectangle extends Shape {

    /**Properties of Rectangle*/
    private final int width;
    private final int height;


    /**Basic Constructor of Rectangle*/
    public Rectangle( int width, int height){
        this.width = width;
        this.height = height;

        this.setShapeDataObject( this.getWidth(), this.getHeight(), this.toString());

    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     **/
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     **/
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Calculates and returns the area of the rectangle.
     *
     * @return the area of the rectangle.
     **/
    @Override
    public double area() {
        return this.width * this.height;
    }

    /**
     * Calculates and returns the perimeter of the rectangle.
     *
     * @return the perimeter of the rectangle.
     **/
    public double perimeter() {
        return 2 * this.width + 2 * this.height;
    }

    /**
     * Returns a string representation of the rectangle.
     * Each row of the rectangle contains width number of asterisks separated by spaces,
     * and the number of rows corresponds to the height of the rectangle.
     * Each line starts and ends with a space.
     *
     * @return a string representation of the rectangle.
     **/
    @Override
    public String toString() {
        String blockInRow = Shape.shapeCell + Shape.spaceBetweenCells;
        String line = Shape.spaceInBound + blockInRow.repeat(this.width - 1) + Shape.shapeCell
                + Shape.spaceInBound + Shape.endLine;
        return line.repeat( this.height );
    }

    /**
     * Checks whether this rectangle is equal to another object.
     * Two rectangles are considered equal if they are of the same class
     * and have the same width and height.
     *
     * @param otherObj the object to compare with.
     * @return true if the object is a rectangle with the same attributes, false if not.
     **/
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        Rectangle otherRectangle = (Rectangle) otherObj;
        return this.width == otherRectangle.width && this.height == otherRectangle.height;
    }

    /**Clone function*/
    @Override
    public Rectangle clone(){
        return new Rectangle(this.width, this.height);
    }

    @Override
    public int hashCode(){
        return 1;
    }

}
