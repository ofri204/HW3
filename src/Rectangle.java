public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public double calculateArea() {
        return this.width * this.height;
    }

    public double calculatePerimeter() {
        return 2 * this.width + 2 * this.height;
    }

    public String show() {
        String cell = " *";
        String line = cell.repeat(this.width);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < height; i++) {
            result.append(line);
            result.append(" ");
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        Rectangle otherRectangle = (Rectangle) otherObj;
        return this.width == otherRectangle.width && this.height == otherRectangle.height;
    }
}
