package basepackage;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SensorTest {
    public static final String SAMPLE_RESPONSE =
            "[{\n" +
            "    \"id\": 92,\n" +
            "    \"stationId\": 14,\n" +
            "    \"param\": {\n" +
            "        \"paramName\": \"pył zawieszony PM10\",\n" +
            "        \"paramFormula\": \"PM10\",\n" +
            "        \"paramCode\": \"PM10\",\n" +
            "        \"idParam\": 3\n" +
            "    }\n" +
            "},\n" +
            "{\n" +
            "    \"id\": 88,\n" +
            "    \"stationId\": 14,\n" +
            "    \"param\": {\n" +
            "        \"paramName\": \"dwutlenek azotu\",\n" +
            "        \"paramFormula\": \"NO2\",\n" +
            "        \"paramCode\": \"NO2\",\n" +
            "        \"idParam\": 6\n" +
            "    }\n" +
            "}]";

    private static Sensor[] instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, Sensor[].class);
    }
    @Test
    public void getId() {
        assertEquals(instance[0].getId(), 92);
        assertEquals(instance[1].getId(), 88);
    }

    @Test
    public void getStatoinID() {
        assertEquals(instance[0].getStationId(), 14);
        assertEquals(instance[1].getStationId(), 14);
    }

    @Test
    public void getParam() {
        assertNotNull(instance[0].getParam());
        assertNotNull(instance[1].getParam());
    }
}