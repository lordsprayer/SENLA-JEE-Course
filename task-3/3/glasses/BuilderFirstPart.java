package glasses;

public class BuilderFirstPart implements ILineStep {

    @Override
    public IProductPart buildProductPart() {
        IProductPart body = new Body("wood", "round");
        System.out.println("Body build");
        return body;
    }
}
