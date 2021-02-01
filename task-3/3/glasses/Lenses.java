package glasses;

public class Lenses implements IProductPart {
    private String diopters;
    private String stuff;

    public Lenses(String diopters, String stuff) {
        this.diopters = diopters;
        this.stuff = stuff;
    }

    public String getDiopters() {
        return diopters;
    }

    public void setDiopters(String diopters) {
        this.diopters = diopters;
    }

    public String getStuff() {
        return stuff;
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }
}
