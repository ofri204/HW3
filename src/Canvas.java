public class Canvas {
    private int width;
    private int height;
    private Shape[][] shapes;
    private int[][] shapesIndexes;
    private int numShapes;


    public Canvas(int width, int height) {

        this.width = width;
        this.height = height;

        this.shapes = new Shape[width][height];
        shapesIndexes = new int[width][height];

        this.numShapes = 0;
    }

    public double getTotalArea(){
    }





}
