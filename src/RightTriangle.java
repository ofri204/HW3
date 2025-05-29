public class RightTriangle extends Shape {
    private int width;
    private int height;

    public RightTriangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public double calculateArea() {
        return (double) (this.width * this.height) / 2;
    }

    @Override
    public double calculatePerimeter() {
        double hypotenuse = Math.sqrt(this.width * this.width + this.height * this.height);
        return this.width + this.height + hypotenuse;
    }


    @Override
    public String show() {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= height; i++) {
            int starsCount = calculateStarsCount(i);
            String line = createLine(starsCount);
            result.append(line);

            if (i < height) {
                result.append("\n");
            }
        }

        return result.toString();
    }


    private int calculateStarsCount(int row) {
        int count = (int)((double) row * width / height);
        if (count == 0) {
            return 1;
        }

        return count;
    }


    private String createLine(int starsCount) {
        String line = " ";

        if (starsCount == 1) {
            line += "*";
        } else {
            line += "* ".repeat(starsCount - 1) + "*";
        }

        line += " ";
        return line;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        RightTriangle otherRightTriangle = (RightTriangle) otherObj;
        return this.width == otherRightTriangle.width && this.height == otherRightTriangle.height;
    }

}
