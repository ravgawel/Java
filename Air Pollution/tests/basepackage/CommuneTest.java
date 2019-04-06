package basepackage;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommuneTest {

    public static final String SAMPLE_RESPONSE =
            "        {\n" +
            "            \"communeName\": \"Bogatynia\",\n" +
            "            \"districtName\": \"zgorzelecki\",\n" +
            "            \"provinceName\": \"DOLNOŚLĄSKIE\"\n" +
            "        }";

    private static Commune instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, Commune.class);
    }

    @Test
    public void getCommuneName() {
        assertEquals(instance.getCommuneName(), "Bogatynia");
    }

    @Test
    public void getDistrictName() {
        assertEquals(instance.getDistrictName(), "zgorzelecki");
    }

    @Test
    public void getProvinceName() {
        assertEquals(instance.getProvinceName(), "DOLNOŚLĄSKIE");
    }
}