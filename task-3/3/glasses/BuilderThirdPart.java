package glasses;

public class BuilderThirdPart implements ILineStep {

    @Override
    public IProductPart buildProductPart() {
        IProductPart pair = new Pair(12);
        System.out.println("Pair build");
        return pair;
    }
}
