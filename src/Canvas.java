public class Canvas {
    private int width;
    private int height;
    private Shape[][] shapes;
    private int[][] shapes_indexes;



    public Canvas(int width, int height) {

        this.width = width;
        this.height = height;

        shapes = new Shape[width][height];
        shapes_indexes = new int[width][height];
    }




}
