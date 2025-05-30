public class Canvas {
    private int width;
    private int height;
    private Shape[][] shapes;
    private int[][] shapesIndexes;
    private int numShapes;


    private static final int INDEX_NOT_EXIST_ERROR = -1;
    private static final int ROW_POS_IN_ARR = 0;
    private static final int COLUMN_POS_IN_ARR = 1;


    public Canvas(int width, int height) {

        this.width = width;
        this.height = height;

        this.shapes = new Shape[width][height];
        shapesIndexes = new int[width][height];

        this.numShapes = 0;
    }

    private boolean hasOutOfBound( int row, int column){
        return row < 0 || column < 0 || this.width <= row || this.height <= column;
    }

    private boolean isIndexExists(int row, int column){
        return findIndexByCoordinates(row, column) != Canvas.INDEX_NOT_EXIST_ERROR;
    }

    private void addIndex( int row, int column ){
        if( !isIndexExists(row, column) ){
            this.shapesIndexes[this.numShapes] = new int[]{ row, column };
            this.numShapes++;
        }
    }


    public void addShape(Shape newShape, int row, int column){

        if( this.hasOutOfBound(row, column) || newShape == null ) { return; }

        this.shapes[row][column] = newShape.clone();
        this.addIndex(row, column);

    }

    private boolean isCoordinatesSame( int row, int column, int index){
        return this.shapesIndexes[index][Canvas.ROW_POS_IN_ARR] == row
                && this.shapesIndexes[index][Canvas.COLUMN_POS_IN_ARR] == column;
    }

    private int findIndexByCoordinates( int row, int column ){
        for( int i = 0; i < this.numShapes; i++ ){
            if ( this.isCoordinatesSame( row, column, i ) ){
                return i;
            }
        }
        return Canvas.INDEX_NOT_EXIST_ERROR;
    }

    private void removeIndex( int row, int column ){
        int posInArr = this.findIndexByCoordinates( row, column );
        this.shapesIndexes[posInArr] = null;
        this.shapesIndexes[posInArr] = this.shapesIndexes[this.numShapes-1];
        this.shapesIndexes[this.numShapes-1] = null;
        this.numShapes--;

    }

    private boolean hasShapeInIndex( int row, int column){
        return this.shapes[row][column] != null;
    }

    public void removeShape( int row, int column ){
        if( this.hasOutOfBound(row, column) || !this.hasShapeInIndex(row, column) ) { return; }
        this.shapes[row][column] = null;
        this.removeIndex(row, column);

    }







}
