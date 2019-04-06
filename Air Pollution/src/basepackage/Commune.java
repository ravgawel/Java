package basepackage;

/**
 * Represents commune objects.
 * Created mainly for the JSON library.
 * Stores commune names, district names and province names
 */
public class Commune {
    private String communeName;
    private String districtName;
    private String provinceName;

    public Commune() {
    }

    public String getCommuneName() {
        return communeName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getProvinceName() {
        return provinceName;
    }
}
