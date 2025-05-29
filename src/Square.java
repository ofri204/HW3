public class Square extends Rectangle {
    private int side;

    public Square(int side) {
        super( side , side);
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) return true;

        if (otherObj == null) return false;

        if (this.getClass() != otherObj.getClass())
            return false;

        Square otherSquare= (Square) otherObj;
        return this.side == otherSquare.side;
    }
}
