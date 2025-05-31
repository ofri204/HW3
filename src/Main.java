public class Main {
    public static void main(String[] args) {
        Canvas can = new Canvas(3 , 4);
        Shape r = new Rectangle(3,4 );
        Shape c = new Circle(5);
        Shape t = new RightTriangle(3, 3);
        Shape s = new Square(3);
        can.addShape(r, 0, 0);
        System.out.println(can.getTotalPerimeter());
    }
}