public class Main {
    public static void main(String[] args) {
        Shape r = new Rectangle(3,4 );
        Shape c = new Circle(5);
        Shape t = new RightTriangle(3, 3);
        Shape s = new Square(3);
        System.out.println(s.show());
    }
}