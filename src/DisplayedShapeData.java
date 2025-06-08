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

    /**<p><u>Get a string of a specific row</u></p>
     * @param numRow number row to get
     * @return string of the row*/
    public String getRow( int numRow){
        return this.rows[numRow];
    }

    /**<p><u>Get the display height of the shape</u></p>
     * @return the display height*/
    public int getDisplayHeight(){
        return this.rowAmount;
    }

    /**<p><u>Get the display width of the shape</u></p>
     * @return the display width*/
    public int getDisplayWidth(){
        return this.columnAmount;
    }

    /**<p><u>Get empty row in the max row length of the shape</u></p>
     * @return string of the max empty row*/
    public String getMaxEmptyRow(){
        return this.maxEmptyRow;
    }




}
