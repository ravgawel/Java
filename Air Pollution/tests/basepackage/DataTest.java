package basepackage;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataTest {
    public static final String SAMPLE_RESPONSE =
            "{\n" +
            "    \"key\": \"PM10\",\n" +
            "    \"values\": [\n" +
            "    {\n" +
            "        \"date\": \"2017-03-28 11:00:00\",\n" +
            "        \"value\": 30.3018\n" +
            "    },\n" +
            "    {\n" +
            "        \"date\": \"2017-03-28 12:00:00\",\n" +
            "        \"value\": 27.5946\n" +
            "    }]\n" +
            "}";

    private static Data instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, Data.class);
    }

    @Test
    public void getKey() {
        assertEquals(instance.getKey(), "PM10");
    }

    @Test
    public void getValues() {
        assertNotNull(instance.getValues());
    }
}