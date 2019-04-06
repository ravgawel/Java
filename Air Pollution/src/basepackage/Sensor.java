package basepackage;

/**
 * Represents sensor objects.
 * Created mainly for the JSON library.
 * Stores id of the sensor, id of the station and the parameter that it examines
 */
public class Sensor {
    private int id;
    private int stationId;
    private Param param;

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public int getStationId() {
        return stationId;
    }

    public Param getParam() {
        return param;
    }
}
