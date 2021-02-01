package glasses;

public class Body implements IProductPart {
    private static String material;
    private static String shape;

    public Body(String material, String shape) {
        this.material = material;
        this.shape = shape;
    }

    public static String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public static String getShape() {
        return shape;
    }
}
