
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

    /**General Strings for printing Canvas*/
    private static final String SPACE_BETWEEN_SHAPES = "   " ;
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

    /**<p><u>Calculate the area of all shapes on the Canvas board</u></p
     * @return total area of all shapes>*/
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

    /**<p><u>Calculate the total perimeter of all shapes in the Canvas board</u></p>
     * @return total perimeter of all shapes*/
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


    /**<p><u>Prints the Canvas board</u></p>
     * @return String of canvas board*/
    @Override
    public String toString(){
        return this.createCanvasBoard();
    }

    /**<p><u>Finds the shape in the canvas board which has the biggest display width</u></p>
     * <br><b>Sub-function of: {@link #createCanvasBoard()}</b>
     * @return the biggest display width
     * */
    private int findMaxDisplayWidth(){
        int maxWidth = 0;
        for( int i = 0; i < this.numShapes; i++ ) {
            int[] shapeCoords = this.shapesCoordinates[i];
            int currentWidth = this.shapes[ shapeCoords[ Canvas.ROW_POS_IN_COORDS] ]
                    [ shapeCoords[Canvas.COLUMN_POS_IN_COORDS] ].getDisplayWidth();
            if( currentWidth > maxWidth){
                maxWidth = currentWidth;
            }
        }
        return  maxWidth;
    }


    /**
     * <p><u>Creates canvas board string</u></p>
     * <br><b>Sub-function of: {@link #toString()}</b>
     * @return canvas board string
     * */
    private String createCanvasBoard(){
        StringBuilder board = new StringBuilder();
        String maxEmptyRow = " ".repeat( this.findMaxDisplayWidth() );
        for( int iRow = 0; iRow < this.rowAmount; iRow++ ){ //creating each row of the canvas board
            board.append( this.createBoardRow( iRow, maxEmptyRow ) );
        }
        return board.toString();
    }

    /**<p><u>Creates Row for Canvas Row String</u></p>
     * <br><b>Sub-function of: {@link #createCanvasBoard()}</b>
     * @param numRow number of row to create
     * @param emptyRow a string of empty cell
     * @return string of the row*/
    private String createBoardRow( int numRow, String emptyRow ){
        StringBuilder row = new StringBuilder();
        if( this.isEmptyBoardRow( numRow ) ){
            row.append( Canvas.EMPTY_ROW );
        } else{
            row.append( this.createNotEmptyCanvasBoardRow( numRow, emptyRow ) ) ;
            row.append( Canvas.EMPTY_ROW );
        }
        return row.toString();
    }

    /**<p><u>Checks if a specific Row in canvas board is empty (means there are no shapes)</u></p>
     *  <br><b>Sub-function of: {@link #createBoardRow(int, String)}</b>
     *  @param rowNum number of row to check
     * @return true if the row is empty, false otherwise*/
    private boolean isEmptyBoardRow( int rowNum ){
        for( int i = 0; i < this.columnAmount; i++){
            if( this.shapes[rowNum][i] != null){
                return false;
            }
        }
        return true;
    }

    /**<p><u>Creates a canvas board row string if there are shape in it</u></p>
     * <br><b>Sub-function of: {@link #createBoardRow(int, String)}</b>
     * @param numRow number of row to create
     * @param emptyRow string of empty cell
     * @return string of the row
     * */
    private String createNotEmptyCanvasBoardRow( int numRow, String emptyRow ){
        StringBuilder canvasBoardRow = new StringBuilder();
        int maxHeight = this.findMaxHeightInRow( numRow );
        for( int i = 0; i < maxHeight; i++){
            String row = this.createRowOfCanvasRow( i, numRow, emptyRow );
            canvasBoardRow.append(row).append(Canvas.EMPTY_ROW);
        }
        return canvasBoardRow.toString();
    }

    /**<p><u>Creates a row of canvas row</u></p>
     * <br><b>Sub-function of: {@link #createNotEmptyCanvasBoardRow(int, String)}</b>
     * @param numRowOfShapeRow number of row of the row
     * @param numOfCanvasRow number of canvas row
     * @param emptyRowLine empty line for empty cells
     * @return string of the row of the canvas row
     * */
    private String createRowOfCanvasRow( int numRowOfShapeRow, int numOfCanvasRow,
                                         String emptyRowLine){
        StringBuilder row = new StringBuilder();

        for( int i = 0; i < this.columnAmount; i++){
            if( i != 0 ){
                row.append( Canvas.SPACE_BETWEEN_SHAPES );
            }

            Shape tempShape = this.shapes[numOfCanvasRow][i];

            if( tempShape == null  ){ //the cell is empty
                    row.append( emptyRowLine );
            } else{
                if(  numRowOfShapeRow < tempShape.getDisplayHeight() ){ // the shape is too short
                    row.append( tempShape.getRow( numRowOfShapeRow) );
                } else{
                    row.append(  tempShape.getMaxEmptyRow() );
                }
            }
        }
        return row.toString();
    }

    /**<p><u>Find max height of a shape display in row</u></p>
     * <br><b>Sub-function of: {@link #createNotEmptyCanvasBoardRow(int, String)}</b>
     * @param numRow number of row
     * @return max height of a shape display in the row*/
    private int findMaxHeightInRow( int numRow ){
        int maxHeight = 0;
        for( int i = 0; i < this.numShapes; i++) { //check all existing shapes
            int[] temp = this.shapesCoordinates[i];
            if (temp[Canvas.ROW_POS_IN_COORDS] == numRow) { //check if the shape is in the row
                Shape tempShape = this.shapes[temp[Canvas.ROW_POS_IN_COORDS]]
                        [temp[Canvas.COLUMN_POS_IN_COORDS]];
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

        if (!isOtherNotNullAndSameClass(otherObj) || Canvas.class != otherObj.getClass()) return false;

        Canvas other = (Canvas) otherObj;

        return isTheSameDimensions(other) && areAllShapesTheSame(other);

    }

}



