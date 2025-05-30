/**
 * Represents a rectangle shape with width and height.
 * Inherits from the abstract Shape class and implementations it's methods.
 **/
public class Rectangle extends Shape {
    private int width;
    private int height;

    /**
     * Constructs a rectangle with width and height.
     *
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     **/
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
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
    public double calculateArea() {
        return this.width * this.height;
    }

    /**
     * Calculates and returns the perimeter of the rectangle.
     *
     * @return the perimeter of the rectangle.
     **/
    public double calculatePerimeter() {
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
    public String show() {
        String cell = " *";
        String line = cell.repeat(this.width);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < height; i++) {
            result.append(line);
            result.append(" ");
            result.append("\n");
        }

        return result.toString();
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
}
