package basepackage;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import static org.junit.Assert.*;

public class StationTest {
    public static final String SAMPLE_RESPONSE =
            "[{\n" +
            "    \"id\": 14,\n" +
            "    \"stationName\": \"Działoszyn\",\n" +
            "    \"gegrLat\": \"50.972167\",\n" +
            "    \"gegrLon\": \"14.941319\",\n" +
            "    \"city\": {\n" +
            "        \"id\": 192,\n" +
            "        \"name\": \"Działoszyn\",\n" +
            "        \"commune\": {\n" +
            "            \"communeName\": \"Bogatynia\",\n" +
            "            \"districtName\": \"zgorzelecki\",\n" +
            "            \"provinceName\": \"DOLNOŚLĄSKIE\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"addressStreet\": null\n" +
            "}]";

    private static Station[] instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, Station[].class);
    }

    @Test
    public void getId() {
        assertEquals(instance[0].getId(), 14);
    }

    @Test
    public void getStationName() {
        assertEquals(instance[0].getStationName(), "Działoszyn");
    }

    @Test
    public void getGegrLat() {
        assertEquals(instance[0].getGegrLat(), "50.972167");
    }

    @Test
    public void getGegrLon() {
        assertEquals(instance[0].getGegrLon(), "14.941319");
    }

    @Test
    public void getCity() {
        assertNotNull(instance[0].getCity());
    }

    @Test
    public void getAddresssStreet() {
        assertEquals(instance[0].getAddresssStreet(), null);
    }
}