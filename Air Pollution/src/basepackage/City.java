package basepackage;

/**
 * Represents city objects.
 * Created mainly for the JSON library.
 * Stores ids, names and communes
 */
public class City {
    private int id;
    private String name;
    private Commune commune;

    public City() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Commune getCommune() {
        return commune;
    }
}
