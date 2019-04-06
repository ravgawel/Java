package basepackage;

/**
 * Represents data objects.
 * Created mainly for the JSON library.
 * Stores keys and values
 */
public class Data {
    private String key;
    private Values values[];

    public Data() {
    }

    public String getKey() {
        return key;
    }

    public Values[] getValues() {
        return values;
    }
}
