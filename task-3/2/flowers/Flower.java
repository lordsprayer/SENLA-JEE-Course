package flowers;

import java.awt.*;

public abstract class Flower {
    private String name;
    private double cost;
    private String color;

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public Flower(String name, double cost, String color){
        this.name = name;
        this.cost = cost;
        this.color =color;
    }

   /* public void setCost(double cost) {
        this.cost = cost;
    }*/
   @Override
   public String toString() {
       return "Name: " + getName() + ", cost: " + getCost() + ", color: " + getColor();
   }
}
