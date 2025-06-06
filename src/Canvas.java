
public class Canvas {

    /**Properties of Canvas Object*/
    private final int columnAmount;
    private final int rowAmount;
    private final Shape[][] shapes;
    private final int[][] shapesCoordinates;
    private int numShapes;


    /**Errors*/
    private static final int INDEX_NOT_EXIST_ERROR = -1;

    /**Coordinates properties*/
    private static final int ROW_POS_IN_COORDS = 0;
    private static final int COLUMN_POS_IN_COORDS = 1;
    private static final int NUM_COORDINATE_PARTS = 2;


    private static final String SPACE_BETWEEN_SHAPES = "123" ;//"   ";
    private static final String EMPTY_ROW = "\n";

    /**Basic Canvas constructor*/
    public Canvas(int rowAmount, int columnAmount) {

        this.rowAmount = rowAmount;
        this.columnAmount = columnAmount;

        this.shapes = new Shape[rowAmount][columnAmount];
        this.shapesCoordinates = new int[rowAmount*columnAmount][NUM_COORDINATE_PARTS];

        this.numShapes = 0;
    }


    /**
     * <p><u>Adds shape to array</u></p>
     * <br><b>Note1: if the {@code newShape} is null, it won't be added.</b>
     * <br><b>Note2: if one the coordinates is out of bound, the shape won't be added.</b>
     * @param newShape a new shape
     * @param numRow the row of the shape in canvas
     * @param numColumn the column of the shape in canvas*/
    public void addShape(Shape newShape, int numRow, int numColumn){

        if( this.hasOutOfBound(numRow, numColumn) || newShape == null ) { return; }

        this.shapes[numRow][numColumn] = newShape.clone();
        this.addCoordinate(numRow, numColumn);
    }

    /**
     * <p><u>Removes a shape from canvas</u></p>
     * <br><b>Note1: if the coordinate is out of bound, no shape will be removed</b>
     * <br><b>Note2: if the shape in the coordinate is null, no shape will be removed</b>
     * @param numRow row of a shape to remove
     * @param numColumn column of a shape to remove
     * */
    public void removeShape( int numRow, int numColumn ){
        if( this.hasOutOfBound(numRow, numColumn) || this.isCoordinateEmpty(numRow, numColumn) ) {
            return;
        }
        this.shapes[numRow][numColumn] = null;
        this.removeCoordinate(numRow, numColumn);
    }

    public double getTotalArea() {
        double totalArea= 0;
        for( int i = 0; i < this.numShapes; i++){
            int numRow = this.shapesCoordinates[i][Canvas.ROW_POS_IN_COORDS];
            int numColumn = this.shapesCoordinates[i][Canvas.COLUMN_POS_IN_COORDS];
            Shape tempShape = this.shapes[numRow][numColumn];
            totalArea += tempShape.area();
        }
        return totalArea;
    }

    public double getTotalPerimeter() {
        double totalPerimeter= 0;
        for( int i = 0; i < this.numShapes; i++){
            int numRow = this.shapesCoordinates[i][Canvas.ROW_POS_IN_COORDS];
            int numColumn = this.shapesCoordinates[i][Canvas.COLUMN_POS_IN_COORDS];
            Shape tempShape = this.shapes[numRow][numColumn];
            totalPerimeter += tempShape.perimeter();
        }
        return totalPerimeter;
    }


    @Override
    public String toString(){
        return this.createCanvasBoard();
    }

    /**
     * <p><u>Creates canvas board string</u></p>
     * @return canvas board string*/
    private String createCanvasBoard(){
        StringBuilder board = new StringBuilder();
        for( int iRow = 0; iRow < this.rowAmount; iRow++ ){ // creating each row of the canvas board
            board.append( this.createBoardRow( iRow ) );
        }
        return board.toString();
    }

    private String createBoardRow( int numRow ){
        StringBuilder row = new StringBuilder();
        if( this.isEmptyBoardRow( numRow ) ){
            row.append( Canvas.EMPTY_ROW );
        } else{
            row.append( this.createNotEmptyCanvasBoardRow( numRow ) ) ;
        }
        row.append( Canvas.EMPTY_ROW );
        return row.toString();
    }

    private boolean isEmptyBoardRow( int rowNum ){
        for( int i = 0; i < this.columnAmount; i++){
            if( this.shapes[rowNum][i] != null){
                return false;
            }
        }
        return true;
    }

    private String createNotEmptyCanvasBoardRow( int numRow ){
        StringBuilder canvasBoardRow = new StringBuilder();
        int maxShapeRowWidth = this.findMaxDisplayedShapeWidth( numRow );
        String emptyRowLine = " ".repeat( maxShapeRowWidth );
        int maxHeight = this.findMaxHeightInRow( numRow );

        for( int i = 0; i < maxHeight; i++){
            String row = this.createRowOfCanvasRow( i, numRow, emptyRowLine );
            canvasBoardRow.append(row).append(Canvas.EMPTY_ROW);
        }

        return canvasBoardRow.toString();
    }

    private String createRowOfCanvasRow( int numRowOfShapeRow, int numOfCanvasRow, String emptyRowLine){
        StringBuilder row = new StringBuilder();

        for( int i = 0; i < this.columnAmount; i++){
            if( i != 0){
                row.append( Canvas.SPACE_BETWEEN_SHAPES );
            }
            Shape tempShape = this.shapes[numOfCanvasRow][i];

            if( tempShape == null  ){
                    row.append( emptyRowLine );
            } else{
                if(  numRowOfShapeRow < tempShape.getDisplayHeight() ){
                    row.append( tempShape.getRow( numRowOfShapeRow) );
                } else{
                    row.append(  tempShape.getMaxEmptyRow());
                }
            }
        }
        return row.toString();
    }

    private int findMaxDisplayedShapeWidth( int row ){
        int maxWidth = 0;
        for( int i = 0; i < this.numShapes; i++){
            int[] coordsArr = this.shapesCoordinates[i];
            if( coordsArr[ Canvas.ROW_POS_IN_COORDS] == row) {
                Shape tempShape = this.shapes[coordsArr[Canvas.ROW_POS_IN_COORDS]][coordsArr[Canvas.COLUMN_POS_IN_COORDS]];
                int tempShapeWidth = tempShape.getDisplayWidth();
                if( tempShapeWidth > maxWidth ) {
                    maxWidth = tempShapeWidth;
                }
            }

        }
        return maxWidth;
    }

    private int findMaxHeightInRow( int row ){
        int maxHeight = 0;
        for( int i = 0; i < this.numShapes; i++) {
            int[] temp = this.shapesCoordinates[i];
            if (temp[Canvas.ROW_POS_IN_COORDS] == row) {
                Shape tempShape = this.shapes[temp[Canvas.ROW_POS_IN_COORDS]][temp[Canvas.COLUMN_POS_IN_COORDS]];
                int tempShapeHeight = tempShape.getDisplayHeight();
                if (tempShapeHeight > maxHeight) {
                    maxHeight = tempShapeHeight;
                }

            }
        }
        return maxHeight;
    }




    /**
     * <p><u>Checks if a coordinate is out of bound</u>
     * <br><b>Sub-function of: {@link #addShape(Shape, int, int)}, 
     * {@link #removeShape(int, int)}</b>
     * @param numRow a row of a coordinate
     * @param numColumn a column of a coordinate
     * @return true if the coordinate is inside the bounds, false otherwise</p>*/
    private boolean hasOutOfBound( int numRow, int numColumn){
        return numRow < 0 || numColumn < 0 || this.columnAmount <= numColumn ||
                this.rowAmount <= numRow;
    }

    /**<p><u>Checks if a coordinate exists in the canvas</u></p>
     * <br><b>Sub-function of: {@link #addCoordinate(int, int)}</b>
     * @param numRow a row of a coordinate
     * @param numColumn a column of a coordinate
     * @return true if the coordinate exists, false otherwise</p>*/
    private boolean isCoordinateExist(int numRow, int numColumn){
        return this.findIndexByCoordinates(numRow, numColumn) != Canvas.INDEX_NOT_EXIST_ERROR;
    }


    /**<p><u>Adds a coordinate of an active shape to canvas</u></p>
     * <br><b>Note: if the coordinate exists, it won't be added</b>
     * <br><b>Sub-function of: {@link #addShape(Shape, int, int)}</b>
     * @param numRow a row of a coordinate
     * @param numColumn a column of a coordinate*/
    private void addCoordinate( int numRow, int numColumn ){
        if( !this.isCoordinateExist(numRow, numColumn) ){
            this.shapesCoordinates[this.numShapes] = new int[]{ numRow, numColumn };
            this.numShapes++;
        }
    }


    /**<p><u><Checks if input coordinate is identical to coordinate from canvas/u></p>
     * <br><b>Sub-function of: {@link #findIndexByCoordinates(int, int)}</b>
     * @param numRow a row of a coordinate
     * @param numColumn a column of a coordinate
     * @param index an index of a coordinate from {@code shapesCoordinates}
     * @return true if they are identical, false otherwise*/
    private boolean isCoordinatesSame( int numRow, int numColumn, int index){
        return this.shapesCoordinates[index][Canvas.ROW_POS_IN_COORDS] == numRow
                && this.shapesCoordinates[index][Canvas.COLUMN_POS_IN_COORDS] == numColumn;
    }

    /**<p><u>Finds an index of a coordinate in canvas</u></p>
     * <br><b>Sub-function of: {@link #isCoordinateExist(int, int)}
     * {@link #removeCoordinate(int, int)}</b>
     * @param numRow a row of a coordinate
     * @param numColumn a column of a coordinate
     * @return index of a coordinate in {@code shapesCoordinates} if it exists,
     * {@code INDEX_NOT_EXIST_ERROR} otherwise*/
    private int findIndexByCoordinates( int numRow, int numColumn ){
        for( int i = 0; i < this.numShapes; i++ ){
            if ( this.isCoordinatesSame( numRow, numColumn, i ) ){
                return i;
            }
        }
        return Canvas.INDEX_NOT_EXIST_ERROR;
    }


    /**<p><u>Removes coordinate from canvas</u></p>
     * <br><b>Sub-function of: {@link #removeShape(int, int)}</b>
     * @param numRow a row of a coordinate
     * @param numColumn a column of a coordinate */
    private void removeCoordinate( int numRow, int numColumn ){
        int posInArr = this.findIndexByCoordinates( numRow, numColumn );
        this.shapesCoordinates[posInArr] = null;
        this.shapesCoordinates[posInArr] = this.shapesCoordinates[this.numShapes-1];
        this.shapesCoordinates[this.numShapes-1] = null;
        this.numShapes--;
    }


    /**<p><u>Checks if a shape in canvas if null</u></p>
     * <br><b>Sub-func of: {@link #removeShape(int, int)}</b>
     * @param numRow  a row of a coordinate
     * @param numColumn a column of a coordinate
     * @return true if the shape is null, false otherwise*/
    private boolean isCoordinateEmpty( int numRow, int numColumn){
        return this.shapes[numRow][numColumn] == null;
    }


    /**Add commentMore actions
     * Helper function for {@link #equals(Object)}.
     * Checks if the given object is not null and if it is of the same class as this object.
     *
     * @param otherObj the object to compare with.
     * @return true if the object is not null and belongs to the same class, false otherwise.
     **/
    private boolean isOtherNotNullAndSameClass(Object otherObj) {
        return otherObj != null && this.getClass() == otherObj.getClass();
    }

    /**
     * Helper function for {@link #equals(Object)}.
     * Checks if this canvas has the same dimensions as the another canvas.
     *
     * @param other the canvas to compare with.
     * @return true if both canvases have the same number of rows and columns, false otherwise.
     **/
    private boolean isTheSameDimensions(Canvas other) {
        return this.rowAmount == other.rowAmount && this.columnAmount == other.columnAmount;
    }

    /**
     * Helper function for {@link #equals(Object)}.
     * Checks if all shapes in this canvas are equal to the corresponding shapes in the other canvas.
     *
     * @param other the canvas to compare with.
     * @return true if all corresponding shapes are equal or both null, false otherwise
     **/
    private boolean areAllShapesTheSame(Canvas other) {
        for (int i = 0; i < rowAmount; i++) {
            for (int j = 0; j < columnAmount; j++) {
                Shape thisShape = this.shapes[i][j];
                Shape otherShape = other.shapes[i][j];

                if (thisShape == null && otherShape != null) return false;
                if (thisShape != null && !thisShape.equals(otherShape)) return false;
            }
        }
        return true;
    }

    /**
     * Checks whether this canvas is equal to another canvas.
     * The equality is based on having the same dimensions and all corresponding shapes being equal.
     *
     * @param otherObj the object to compare with.
     * @return true if the objects are equal, false otherwise.
     **/
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;

        if (! isOtherNotNullAndSameClass(otherObj)) return false;

        Canvas other = (Canvas) otherObj;

        return isTheSameDimensions(other) && areAllShapesTheSame(other);

    }

}



