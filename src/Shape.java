/**
 * An abstract class representing a shape.
 * All subclasses of Shape must implement the following defined methods.
 **/
public abstract class Shape {

    /**
     * Returns the height of the shape.
     *
     * @return the height.
     **/
    public abstract int getHeight();

    /**
     * Returns the width of the shape.
     *
     * @return the width.
     **/
    public abstract int getWidth();

    /**
     * Calculates the area of the shape.
     *
     * @return the area.
     **/
    public abstract double calculateArea();

    /**
     * Calculates the perimeter of the shape.
     *
     * @return the perimeter.
     **/
    public abstract double calculatePerimeter();

    /**
     * Returns a string representation of the shape.
     * Each shape should implement its own drawing logic using asterisks.
     *
     * @return a string that visually represents the shape.
     **/
    public abstract String show();

    /**
     * Checks whether this shape is equal to another object.
     * Two shapes are considered equal if they are of the same type
     * and have the same attributes.
     *
     * @param obj the object to compare with this shape
     * @return true if they are found equal, and returns false otherwise
     **/
    public abstract boolean equals(Object obj);

}
