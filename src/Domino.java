public class Domino {
    private int side1;
    private int side2;

    public Domino(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }


    public int getSide1() {
        return side1;
    }

    public int getSide2() {
        return side2;
    }

    public void rotate() {
        int temp = side1;
        side1 = side2;
        side2 = temp;
    }


    public String toString() {
        return "[" + side1 + "|" + side2 + "]";
    }
}
