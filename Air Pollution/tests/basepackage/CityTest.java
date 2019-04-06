package basepackage;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityTest {
    public static final String SAMPLE_RESPONSE =
            "    {\n" +
            "        \"id\": 192,\n" +
            "        \"name\": \"Działoszyn\",\n" +
            "        \"commune\": {\n" +
            "            \"communeName\": \"Bogatynia\",\n" +
            "            \"districtName\": \"zgorzelecki\",\n" +
            "            \"provinceName\": \"DOLNOŚLĄSKIE\"\n" +
            "        }\n" +
            "    }\n";

    private static City instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, City.class);
    }
    @Test
    public void getId() {
        assertEquals(instance.getId(), 192);
    }

    @Test
    public void getName() {
        assertEquals(instance.getName(), "Działoszyn");
    }

    @Test
    public void getCommune() {
        assertNotNull(instance.getCommune());
    }
}