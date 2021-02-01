package glasses;

public class BuilderSecondPart implements ILineStep {

    @Override
    public IProductPart buildProductPart() {
        IProductPart lenses = new Lenses("- 3.5", "shape");
        System.out.println("Lenses build");
        return lenses;
    }
}
