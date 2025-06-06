/**
 * Represents a right triangle shape with width and height.
 * Inherits from the abstract Shape class and implements it's methods.
 **/
public class RightAngleTriangle extends Shape {
    /**Properties of RightAngelTriangle*/
    private final int width;
    private final int height;

    /**
     * Constructs a right triangle with the width and height.
     *
     * @param width the base of the triangle.
     * @param height the height of the triangle.
     **/
    public RightAngleTriangle(int width, int height) {
        this.width = width;
        this.height = height;
        this.setShapeDataObject( this.getWidth(), this.getHeight(), this.toString());
    }

    /**
     * Returns the width (base) of the triangle.
     *
     * @return the width of the triangle.
     **/
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the triangle.
     *
     * @return the height of the triangle.
     **/
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * Calculates and returns the area of the triangle.
     *
     * @return the area of the triangle
     **/
    @Override
    public double area() {
        return (double) (this.width * this.height) / 2;
    }

    /**
     * Calculates and returns the perimeter of the triangle.
     * The hypotenuse is calculated using the Pythagorean theorem.
     *
     * @return the perimeter of the triangle
     **/
    @Override
    public double perimeter() {
        double hypotenuse = Math.sqrt(this.width * this.width + this.height * this.height);
        return this.width + this.height + hypotenuse;
    }

    /**
     * Returns a string representation of the triangle using asterisks.
     * Each row starts and ends with a space, and asterisks are separated by single spaces.
     * The number of asterisks in each row is calculated based on the width and height.
     *
     * @return a string representation of the triangle
     **/
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= height; i++) {
            int startNumber = calNumberStartsInRow(i);
            String line = createLine(startNumber);
            result.append(line);
        }
        return result.toString();
    }

    /**
     * A helper method for (@link show)
     * Calculates the number of asterisks to print in a given row,
     * based on the proportion of the row's index to the triangle's height.
     *
     * @param row the current row number (starting from 1)
     * @return the number of asterisks for this row (at least 1)
     **/
    private int calNumberStartsInRow(int row) {
        if( this.height == 0  || this.width == 0){ return 0; }
        int numberStars = (int)((double) row * width / height);
        if( numberStars == 0 ){
            numberStars = 1;
        }
        return numberStars;
    }

    /**
     * A helper method for (@link show)
     * Constructs a string for a row with the specified number of asterisks.
     * Each line starts and ends with a space, and asterisks are separated by single spaces.
     *
     * @return the formatted string line.
     **/
    private String createLine(int starsNum) {
        String blockInLine = Shape.shapeCell + Shape.spaceBetweenCells;
        String tempStr = Shape.spaceInBound + blockInLine.repeat( starsNum - 1 ) + Shape.shapeCell;
        int diffrence = (this.width)*3 - tempStr.length();
        tempStr += Shape.spaceCell.repeat(  diffrence ) +Shape.endLine ;
        return tempStr;
    }

    /**
     * Checks whether this triangle is equal to another object.
     * Two right triangles are considered equal if they are of the same class and have the same width and height.
     *
     * @param otherObj the object to compare with.
     * @return true if the objects are equal, false otherwise.
     **/
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        RightAngleTriangle otherRightTriangle = (RightAngleTriangle) otherObj;
        return this.width == otherRightTriangle.width && this.height == otherRightTriangle.height;
    }




    /**Clone function*/
    @Override
    public RightAngleTriangle clone(){
        return new RightAngleTriangle(this.width, this.height);
    }


}
