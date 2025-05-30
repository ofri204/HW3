/**
 * Represents a square shape, which is a special case of a rectangle
 * where the width and height (side lengths) are equal.
 **/
public class Square extends Rectangle {
    private int side;

    /**
     * Constructs a square with the given side length.
     *
     * @param side the side length of the square.
     */
    public Square(int side) {
        super( side , side);
        this.side=side;
    }


    /**
     * Compares this square to another object for equality.
     * Two squares are considered equal if they are of the same class and have the same side length.
     *
     * @param otherObj the object to compare to.
     * @return true if the objects are the same square with equal side length, false otherwise.
     **/
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        Square otherSquare= (Square) otherObj;
        return this.getWidth() == otherSquare.getWidth();
    }
}
