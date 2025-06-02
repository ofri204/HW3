/**
 * An abstract class representing a shape.
 * All subclasses of Shape must implement the following defined methods.
 **/
public abstract class Shape implements Cloneable{

    /**Cell properties*/
    protected static final String shapeCell = "*";
    protected static final String spaceCell = " ";
    protected static final String endLine = "\n";
    private static final int sizeSpaceBetweenCells = 2;
    private static final int sizeSpaceInBounds = 1;
    protected static final String spaceBetweenCells = Shape.spaceCell.repeat( Shape.sizeSpaceBetweenCells);
    protected static final String spaceInBound = Shape.spaceCell.repeat( Shape.sizeSpaceInBounds );

    /**Properties for analyzing shape string*/
    private DisplayedShapeData shapeData;

    /**Create {@code shapeData}
     * <br> Note: this function can set data shape once (since shape is immutable object)
     * @param width width of the shape
     * @param height height of the shape
     * @param shapeStr shape's String
     * */
    protected final void setShapeDataObject( int width, int height, String shapeStr ){
        if( this.shapeData == null ){
            this.shapeData = new DisplayedShapeData( width,  height,  shapeStr);
        }
    }

    /**Get a row of shapes string
     * @param rowNumber
     * @return string of the row */
    protected final String cutRowFromString( int rowNumber ){
        return shapeData.cutRowNumber( rowNumber);
    }

    protected final int calculateRowLength( int rowNumber ){
        return this.shapeData.calculateRowLen( rowNumber );
    }

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
    public abstract double area();

    /**
     * Calculates the perimeter of the shape.
     *
     * @return the perimeter.
     **/
    public abstract double perimeter();

    /**
     * Returns a string representation of the shape.
     * Each shape should implement its own drawing logic using asterisks.
     *
     * @return a string that visually represents the shape.
     **/
    public abstract String toString();

    /**
     * Checks whether this shape is equal to another object.
     * Two shapes are considered equal if they are of the same type
     * and have the same attributes.
     *
     * @param obj the object to compare with this shape
     * @return true if they are found equal, and returns false otherwise
     **/
    public abstract boolean equals(Object obj);


    /**Clone function*/
    @Override
    public abstract Shape clone();
}
