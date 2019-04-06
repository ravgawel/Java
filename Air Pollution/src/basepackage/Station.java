package basepackage;

/**
 * Represents station objects.
 * Created mainly for the JSON library.
 * Stores ids, station names, locations(gegrLat and gegrLon), city, and street adressess
 */
public class Station {
    private int id;
    private String stationName;
    private String gegrLat;
    private String gegrLon;
    private City city;
    private String addresssStreet;

    public int getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public String getGegrLat() {
        return gegrLat;
    }

    public String getGegrLon() {
        return gegrLon;
    }

    public City getCity() {
        return city;
    }

    public String getAddresssStreet() {
        return addresssStreet;
    }
}
