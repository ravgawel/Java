package basepackage;

/**
 * Represents index objects.
 * Created mainly for the JSON library.
 * Stores ids, IndexLevel and informations about date of measurement and calculations
 */
public class Index {
    private int id;
    private String stCalcDate;
    private StIndexLevel stIndexLevel;
    private String stSourceDataDate;

    public Index() {
    }

    public int getId() {
        return id;
    }

    public String getStCalcDate() {
        return stCalcDate;
    }

    public StIndexLevel getStIndexLevel() {
        return stIndexLevel;
    }

    public String getStSourceDataDate() {
        return stSourceDataDate;
    }
}
