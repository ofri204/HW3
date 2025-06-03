/**The purpose of the class is to analyze the string of a shape, and help to get easier access to it*/
public class DisplayedShapeData {

    /**Properties of DisplayedShapeData*/
    private  int rowAmount;
    private final int columnAmount;
    private final int shapeStrLength;
    private final String shapeStr;
    private final int[] rowsEndsIndexes;
    private final String shapeClassName;
    private final String maxEmptyRow;


    /**DisplayedShapeData Class Properties*/
    private static final int SPACE_BETWEEN_SHAPE_CELLS = 1;
    private static final int SPACE_BETWEEN_ROWS = 2;

    /**Basic Constructor of DisplayedShapeData*/
    public DisplayedShapeData( String shapeClassName, int width, int height, String shapeStr ){
        this.shapeClassName = shapeClassName;
        this.rowAmount = height;
        this.columnAmount = width * (DisplayedShapeData.SPACE_BETWEEN_SHAPE_CELLS+1);
        this.shapeStr = shapeStr;
        this.shapeStrLength = this.shapeStr.length();

        if( this.shapeClassName.equals("Circle") ){
            this.rowAmount++;
        }

        this.rowsEndsIndexes = new int[this.rowAmount];
        this.maxEmptyRow = " ".repeat( this.columnAmount );

        this.setAllRowsEndings();//find all indexes where row is ending (with '\n')
        this.rowsEndsIndexes[ this.rowAmount - 1] = this.shapeStrLength - 1;
    }


    public String getMaxEmptyRow(){
        return this.maxEmptyRow;
    }

    /**<p><u>Finds all indexes where a row ends and store them in {@code rowEndsIndexes}</u></p>*/
    private void setAllRowsEndings(){
        int rowIndex = 0;
        char rowEnd = Shape.endLine.charAt(0);
        for( int i = 0; i < this.shapeStrLength - 1 && rowIndex < this.rowAmount; i++){
            if( this.shapeStr.charAt(i) == rowEnd){
                this.rowsEndsIndexes[rowIndex] = i;
                rowIndex++;
            }
        }
    }

    /**<p><u>Cuts and returns a specific row of the shape display</u></p>
     * @param numRow number of row to cut
     * @return string of the row to cut*/
    public String cutRowNumber( int numRow ){
        if( numRow > this.rowAmount || numRow < 0){ //row doesn't exist
            return "";
        }

        int startIndex, endIndex;
        if( numRow == 0 ){ //first start is at index 0
            startIndex = 0;
            endIndex = this.rowsEndsIndexes[0];
        } else{
            startIndex = this.rowsEndsIndexes[numRow - 1] + 1;
            endIndex = this.rowsEndsIndexes[numRow];
        }

        return this.cutPartOfString( startIndex, endIndex );
    }

    /**<p><u>Cut part of string between specific indexes</u></p>
     * @param startIndex index to start cutting from
     * @param endIndex last index to cut from
     * @return sub-string of {@code shapeStrLength}*/
    private String cutPartOfString( int startIndex, int endIndex ){
        if( startIndex < 0 || endIndex < 0 ||
                startIndex >= this.shapeStrLength || endIndex >= this.shapeStrLength ) { return "";}
        StringBuilder str = new StringBuilder();
        for( int i = startIndex; i < endIndex; i++){
            str.append(this.shapeStr.charAt(i));
        }
        return str.toString();
    }

    public int calculateRowLen( int numRow ){
        if( numRow > this.rowAmount || numRow < 0){ //row doesn't exist
            return 0;
        }

        int startIndex, endIndex;
        if( numRow == 0 ){ //first start is at index 0
            startIndex = 0;
            endIndex = this.rowsEndsIndexes[0];
        } else{
            startIndex = this.rowsEndsIndexes[numRow - 1] + 1;
            endIndex = this.rowsEndsIndexes[numRow];
        }

        return endIndex - startIndex;
    }

}
