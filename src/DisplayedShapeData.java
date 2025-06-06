/**The purpose of the class is to analyze the string of a shape, and help to get easier access to it*/
public class DisplayedShapeData {

    /**Properties of DisplayedShapeData*/
    private int rowAmount;
    private int columnAmount;
    private final String maxEmptyRow;
    private final String[] rows;

    /**Basic Constructor of DisplayedShapeData*/
    public DisplayedShapeData( String shapeClassName, int width, int height, String shapeStr ){
        this.rowAmount = height;
        this.columnAmount = width * 3;

        if( shapeClassName.equals("Circle") ){
            this.rowAmount++;
            this.columnAmount+=3;
        }

        this.maxEmptyRow = " ".repeat( this.columnAmount );

        this.rows = shapeStr.split("\n");
    }

    public String getRow( int numRow){
        return this.rows[numRow];
    }
    public int getDisplayHeight(){
        return this.rowAmount;
    }
    public int getDisplayWidth(){
        return this.columnAmount;
    }

    public String getMaxEmptyRow(){
        return this.maxEmptyRow;
    }

}
