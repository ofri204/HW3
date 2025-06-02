/**
 * Represents a circle shape with a radius.
 * Inherits from the abstract Shape class and provides implementations for its methods.
 **/
public class Circle extends Shape {

    public static final double PI = Math.PI;
    private final int radius;

    /**
     * Constructs a circle with radius.
     *
     * @param radius the radius of the circle.
     **/
    public Circle(int radius) {

        this.radius = radius;
        this.setShapeDataObject( this.getWidth(), this.getHeight(), this.toString());
    }

    /**
     * Returns the height of the circle.
     *
     * @return the height of the circle.
     */
    @Override
    public int getHeight() {
        return 2 * this.radius + 1;
    }

    /**
     * Returns the width of the circle.
     *
     * @return the width of the circle.
     **/
    @Override
    public int getWidth() { return 2 * this.radius; }

    /**
     * Calculates and returns the area of the circle.
     *
     * @return the area of the circle.
     **/
    @Override
    public double area() {
        return PI * this.radius * this.radius;
    }

    /**
     * Calculates and returns the perimeter of the circle.
     *
     * @return the perimeter of the circle.
     **/
    @Override
    public double perimeter() {
        return 2 * PI * this.radius;
    }

    /**
     * Returns a string representation of the circle using asterisks.
     * Each point is drawn based on its distance from the center (0,0).
     * Each line begins with a space and all asterisks are separated by a single space.
     *
     * @return a string representation of the circle.
     **/
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int r= this.radius;

        for (int y = r; y >= -r; y--) {
            result.append( Shape.spaceInBound );
            for (int x = r; x >= -r; x--)  {
                double distance = Math.sqrt(x * x + y * y);
                if (distance <= radius + 0.3) {
                    result.append(Shape.shapeCell);
                } else {
                    result.append(Shape.spaceCell);
                }

                if( x > -r){
                    result.append(Shape.spaceBetweenCells);
                } else {
                    result.append( Shape.spaceInBound);
                }
            }
            result.append(Shape.endLine);
        }

        return result.toString();
    }

    /**
     * Checks whether this circle is equal to another object.
     * Two circles are considered equal if they are of the same class and have the same radius.
     *
     * @param otherObj the object to compare with.
     * @return true if the other object is a circle with the same radius, false otherwise.
     **/
    @Override
    public boolean equals(Object otherObj){
        if (this== otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        Circle other = (Circle) otherObj;
        return this.radius == other.radius;

    }

    @Override
    public Circle clone(){
        return new Circle( this.radius );
    }

}
