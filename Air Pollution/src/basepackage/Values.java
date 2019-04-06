package basepackage;

import java.util.Date;

/**
 * Represents values objects.
 * Created mainly for the JSON library.
 * Stores data and value
 */
public class Values {
    private Date date;
    private float value;

    public Values() {
    }

    public Date getDate() {
        return date;
    }

    public float getValue() {
        return value;
    }
}
