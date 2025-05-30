public class Canvas {
    private int width;
    private int height;
    private Shape[][] shapes;
    private int[][] shapesCoordinates;
    private int numShapes;


    private static final int INDEX_NOT_EXIST_ERROR = -1;
    private static final int ROW_POS_IN_ARR = 0;
    private static final int COLUMN_POS_IN_ARR = 1;


    public Canvas(int width, int height) {

        this.width = width;
        this.height = height;

        this.shapes = new Shape[width][height];
        shapesCoordinates = new int[width][height];

        this.numShapes = 0;
    }


    /**
     * <p><u>Adds shape to array</u></p>
     * <br><b>Note1: if the {@code newShape} is null, it won't be added.</b>
     * <br><b>Note2: if one the coordinates is out of bound, the shape won't be added.</b>
     * @param newShape a new shape
     * @param row the row of the shape in canvas
     * @param column the column of the shape in canvas*/
    public void addShape(Shape newShape, int row, int column){

        if( this.hasOutOfBound(row, column) || newShape == null ) { return; }

        this.shapes[row][column] = newShape.clone();
        this.addCoordinate(row, column);

    }

    /**
     * <p><u>Removes a shape from canvas</u></p>
     * <br><b>Note1: if the coordinate is out of bound, no shape will be removed</b>
     * <br><b>Note2: if the shape in the coordinate is null, no shape will be removed</b>
     * @param row row of a shape to remove
     * @param column column of a shape to remove
     * */
    public void removeShape( int row, int column ){
        if( this.hasOutOfBound(row, column) || !this.hasShapeInIndex(row, column) ) { return; }
        this.shapes[row][column] = null;
        this.removeCoordinate(row, column);

    }

    /**
     * <p><u>Checks if a coordinate is out of bound</u>
     * <br><b>Sub-function of: {@link #addShape(Shape, int, int)}, 
     * {@link #removeShape(int, int)}</b>
     * @param row a row of a coordinate
     * @param column a column of a coordinate
     * @return true if the coordinate is inside the bounds, false otherwise</p>*/
    private boolean hasOutOfBound( int row, int column){
        return row < 0 || column < 0 || this.width <= row || this.height <= column;
    }

    /**<p><u>Checks if a coordinate exists in the canvas</u></p>
     * <br><b>Sub-function of: {@link #addCoordinate(int, int)}</b>
     * @param row a row of a coordinate
     * @param column a column of a coordinate
     * @return true if the coordinate exists, false otherwise</p>*/
    private boolean isCoordinateExist(int row, int column){
        return findIndexByCoordinates(row, column) != Canvas.INDEX_NOT_EXIST_ERROR;
    }


    /**<p><u>Adds a coordinate of an active shape to canvas</u></p>
     * <br><b>Note: if the coordinate exists, it won't be added</b>
     * <br><b>Sub-function of: {@link #addShape(Shape, int, int)}</b>
     * @param row a row of a coordinate
     * @param column a column of a coordinate*/
    private void addCoordinate( int row, int column ){
        if( !isCoordinateExist(row, column) ){
            this.shapesCoordinates[this.numShapes] = new int[]{ row, column };
            this.numShapes++;
        }
    }


    /**<p><u><Checks if input coordinate is identical to coordinate from canvas/u></p>
     * <br><b>Sub-function of: {@link #findIndexByCoordinates(int, int)}</b>
     * @param row a row of a coordinate
     * @param column a column of a coordinate
     * @param index an index of a coordinate from {@code shapesCoordinates}
     * @return true if they are identical, false otherwise*/
    private boolean isCoordinatesSame( int row, int column, int index){
        return this.shapesCoordinates[index][Canvas.ROW_POS_IN_ARR] == row
                && this.shapesCoordinates[index][Canvas.COLUMN_POS_IN_ARR] == column;
    }

    /**<p><u>Finds an index of a coordinate in canvas</u></p>
     * <br><b>Sub-function of: {@link #isCoordinateExist(int, int)}
     * {@link #removeCoordinate(int, int)}</b>
     * @param row a row of a coordinate
     * @param column a column of a coordinate
     * @return index of a coordinate in {@code shapesCoordinates} if it exists,
     * {@code INDEX_NOT_EXIST_ERROR} otherwise*/
    private int findIndexByCoordinates( int row, int column ){
        for( int i = 0; i < this.numShapes; i++ ){
            if ( this.isCoordinatesSame( row, column, i ) ){
                return i;
            }
        }
        return Canvas.INDEX_NOT_EXIST_ERROR;
    }


    /**<p><u>Removes coordinate from canvas</u></p>
     * <br><b>Sub-function of: {@link #removeShape(int, int)}</b>
     * @param row a row of a coordinate
     * @param column a column of a coordinate */
    private void removeCoordinate( int row, int column ){
        int posInArr = this.findIndexByCoordinates( row, column );
        this.shapesCoordinates[posInArr] = null;
        this.shapesCoordinates[posInArr] = this.shapesCoordinates[this.numShapes-1];
        this.shapesCoordinates[this.numShapes-1] = null;
        this.numShapes--;

    }


    /**<p><u>Checks if a shape in canvas if null</u></p>
     * <br><b>Sub-func of: {@link #removeShape(int, int)}</b>
     * @param row a row of a coordinate
     * @param column a column of a coordinate
     * @return true if the shape isn't null, false otherwise*/
    private boolean hasShapeInIndex( int row, int column){
        return this.shapes[row][column] != null;
    }








}
