package glasses;

public class AssemblyLine implements  IAssemblyLine {
    private ILineStep bodyLineStep;
    private ILineStep lensesLineStep;
    private ILineStep pairLineStep;

    public AssemblyLine (ILineStep bodyLineStep, ILineStep lensesLineStep, ILineStep pairLineStep) {
        this.bodyLineStep = bodyLineStep;
        this.lensesLineStep = lensesLineStep;
        this.pairLineStep = pairLineStep;
    }

    @Override
    public IProduct assembleProduct(IProduct iProduct) {
        iProduct.installFirstPart(bodyLineStep.buildProductPart());
        System.out.println("Body install");
        iProduct.installSecondPart(lensesLineStep.buildProductPart());
        System.out.println("Lenses install");
        iProduct.installThirdPart(pairLineStep.buildProductPart());
        System.out.println("Pair install");
        System.out.println("Glasses ready");
        return iProduct;
    }

    public ILineStep getBodyLineStep() {
        return bodyLineStep;
    }

    public void setBodyLineStep(ILineStep bodyLineStep) {
        this.bodyLineStep = bodyLineStep;
    }

    public ILineStep getLensesLineStep() {
        return lensesLineStep;
    }

    public void setLensesLineStep(ILineStep lensesLineStep) {
        this.lensesLineStep = lensesLineStep;
    }

    public ILineStep getPairLineStep() {
        return pairLineStep;
    }

    public void setPairLineStep(ILineStep pairLineStep) {
        this.pairLineStep = pairLineStep;
    }
}
