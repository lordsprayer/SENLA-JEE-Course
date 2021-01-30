package flowers;

//import java.util.ArrayList;
//import java.util.List;

public class Main {
    public static void main(String[] args) {
        Flower rose = new Rose();
        Flower lily = new Lily();
        Flower narcissus = new Narcissus();
        Flower aster = new Aster();
        Flower aster1 = new Aster(4, "green");
        Flower tulip = new Tulip();
        //List<Flower> list = new ArrayList<Flower>();
        Bouquet bouquet = BouquetCalculator.addToBouquet(rose, lily, narcissus, aster, tulip, aster1);
    }
}
