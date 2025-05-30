public class Canvas {
    private int width;
    private int height;
    private Shape[][] shapes;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        shapes = new Shape[width][height];
    }
}
