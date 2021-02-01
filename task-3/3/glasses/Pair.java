package glasses;

public class Pair implements IProductPart {
    private double size;

    public Pair (double size) {
        this.size =size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
