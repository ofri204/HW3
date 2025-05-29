public class Circle extends Shape {

    public static final double PI = Math.PI;

    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    @Override
    public int getHeight() {
        return 2 * this.radius;
    }

    @Override
    public int getWidth() {
        return 2 * this.radius;
    }

    @Override
    public double calculateArea() {
        return PI * this.radius * this.radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * PI * this.radius;
    }

    public String show() {
        StringBuilder result = new StringBuilder();
        int r= this.radius;

        for (int y = r; y >= -r; y--) {
            result.append(" ");
            for (int x = r; x >= -r; x--)  {
                double distance = Math.sqrt(x * x + y * y);
                if (distance <= radius + 0.3) {
                    result.append("* ");
                } else {
                    result.append("  ");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object otherObj){
        if (this== otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        Circle other = (Circle) otherObj;
        return this.radius == other.radius;

    }

}
