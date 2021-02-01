package glasses;

public class Glasses implements IProduct{
    private IProductPart body;
    private IProductPart lenses;
    private IProductPart pair;

    @Override
    public  void installFirstPart (IProductPart body){
        if (body instanceof Body){
            setBody(body);
        }
        /*else
            System.out.println("Wrong detail");*/
    }

    @Override
    public  void installSecondPart (IProductPart lenses){
        if (lenses instanceof Lenses){
            setLenses(lenses);
        }
    }

    @Override
    public  void installThirdPart (IProductPart pair){
        if (pair instanceof Pair){
            setPair(pair);
        }
    }

   /* @Override
    public String toString(){
            return "New glasses\nbody material " + Body.getMaterial() + ", body  shape " + Body.getShape();

    }*/

    public IProductPart getBody() {
        return body;
    }

    public void setBody(IProductPart body) {
        this.body = body;
    }

    public IProductPart getLenses() {
        return lenses;
    }

    public void setLenses(IProductPart lenses) {
        this.lenses = lenses;
    }

    public IProductPart getPair() {
        return pair;
    }

    public void setPair(IProductPart pair) {
        this.pair = pair;
    }
}
