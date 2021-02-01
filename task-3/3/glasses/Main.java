package glasses;

public class Main {
    public static void main(String[] args) {
        BuilderFirstPart body = new BuilderFirstPart();
        BuilderSecondPart lenses = new BuilderSecondPart();
        BuilderThirdPart pair = new BuilderThirdPart();
        AssemblyLine glassesLine = new AssemblyLine( body, lenses, pair);
        IProduct glasses = new Glasses();
        glasses = glassesLine.assembleProduct(glasses);
        System.out.println(glasses);
    }
}
