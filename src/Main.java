public class Main {

    public static void main(String[] args) {
        //test1();
        test2();
        /*test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();*/
    }

    public static void test1() {
        System.out.println("--- Test 1 - Rectangle and Square ---");

        Rectangle rectangle = new Rectangle(5, 10);
        Square square = new Square(7);

        System.out.println("Rectangle Area: " + rectangle.area());
        System.out.println("Rectangle Perimeter: " + rectangle.perimeter());
        System.out.println(rectangle);

        System.out.println("Square Area: " + square.area());
        System.out.println("Square Perimeter: " + square.perimeter());
        System.out.println(square);

        System.out.println("Rectangle equals Square: " + rectangle.equals(square));

        System.out.println("------------------------------------");


    }

    public static void test2() {
        System.out.println("--- Test 2 - Right Angle Triangle ---");

        RightAngleTriangle triangle = new RightAngleTriangle(3, 4);

        System.out.println("Triangle Area: " + triangle.area());
        System.out.println("Triangle Perimeter: " + triangle.perimeter());
        System.out.println(triangle);

        System.out.println("------------------------------------");
    }

    public static void test3() {
        System.out.println("--- Test 3 - Circle ---");
        Circle circle = new Circle(5);

        System.out.println("Circle Area: " + circle.area());
        System.out.println("Circle Perimeter: " + circle.perimeter());
        System.out.println(circle);

        System.out.println("------------------------------------");
    }

    public static void test4() {
        System.out.println("--- Test 4 - Canvas (3x3) ---");

        Canvas canvas = new Canvas(3, 3);
        canvas.addShape(new Rectangle(2, 3), 0, 0);
        canvas.addShape(new Circle(2), 1, 1);
        canvas.addShape(new RightAngleTriangle(4, 3), 2, 2);

        System.out.println(canvas);
        System.out.println("Total Area: " + canvas.getTotalArea());
        System.out.println("Total Perimeter: " + canvas.getTotalPerimeter());

        Canvas canvas2 = new Canvas(3,3);
        canvas2.addShape(new Rectangle(2,3),0,0);
        canvas2.addShape(new Circle(2),1,1);
        canvas2.addShape(new RightAngleTriangle(4,3),2,2);
        System.out.println("canvas equals canvas2: " + canvas.equals(canvas2));

        canvas2.removeShape(1,1);
        System.out.println("canvas equals canvas2 after remove: " + canvas.equals(canvas2));

        System.out.println("------------------------------------");
    }

    public static void test5() {
        System.out.println("--- Test 5 - Canvas (1x1) ---");

        Canvas canvas = new Canvas(1, 1);
        canvas.addShape(new Rectangle(1, 1), 0, 0);

        System.out.println(canvas);

        Canvas emptyCanvas = new Canvas(0, 0);
        System.out.println("Empty Canvas:\n" + emptyCanvas);

        System.out.println("------------------------------------");
    }

    public static void test6() {
        System.out.println("--- Test 6 - Canvas (2x2) ---");

        Canvas canvas = new Canvas(2, 2);

        System.out.println(canvas);
        System.out.println("Canvas Total Area: " + canvas.getTotalArea());
        System.out.println("Canvas Total Perimeter: " + canvas.getTotalPerimeter());

        System.out.println("------------------------------------");
    }

    public static void test7() {
        System.out.println("--- Test 7- Canvas (2x2) ---");

        Canvas canvas = new Canvas(2, 2);
        Rectangle rect1 = new Rectangle(2, 2);
        Rectangle rect2 = new Rectangle(3, 3);

        canvas.addShape(rect1, 0, 0);
        canvas.addShape(rect2, 1, 1);

        System.out.println("Canvas before remove:\n" + canvas);

        canvas.removeShape(0, 0);
        System.out.println("Canvas after remove:\n" + canvas);

        System.out.println("------------------------------------");
    }

    public static void test8() {
        System.out.println("--- Test 8 - Canvas (5x5) ---");

        Canvas canvas = new Canvas(5, 5);
        canvas.addShape(new Circle(3), 0, 0);
        canvas.addShape(new Rectangle(4, 2), 2, 2);
        canvas.addShape(new RightAngleTriangle(5, 4), 4, 4);

        System.out.println(canvas);
        System.out.println("Canvas Total Area: " + canvas.getTotalArea());
        System.out.println("Canvas Total Perimeter: " + canvas.getTotalPerimeter());

        System.out.println("------------------------------------");
    }

    public static void test9() {

        System.out.println("--- Test 9 - Canvas (3x4) ---");

        Canvas canvas = new Canvas(3, 4);

        canvas.addShape(new Rectangle(5, 4), 0, 0);
        canvas.addShape(new Circle(3), 0, 2);
        canvas.addShape(new RightAngleTriangle(5, 5), 1, 1);
        canvas.addShape(new Square(6), 2, 3);
        canvas.addShape(new Rectangle(3, 2), 2, 0);

        System.out.println(canvas);
        System.out.println("Canvas Total Area: " + canvas.getTotalArea());
        System.out.println("Canvas Total Perimeter: " + canvas.getTotalPerimeter());

        System.out.println("------------------------------------");
    }
}