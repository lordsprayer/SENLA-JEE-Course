package flowers;

import java.util.ArrayList;
import java.util.List;

public class BouquetCalculator {
    public static Bouquet addToBouquet (Flower ... flower){
        List<Flower> list = new ArrayList<Flower>();
        double cost=0;
        for(Flower flo : flower){
            list.add(flo);
            cost += flo.getCost();
            System.out.println(flo);
        }
        System.out.printf("Total cost %.2f", cost);
        Bouquet bouq = new Bouquet(list);
        return bouq;
    }
}
