package basepackage;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IndexTest {
    public static final String SAMPLE_RESPONSE =
            "{\n" +
            "    \"id\": 52,\n" +
            "    \"stCalcDate\": \"2017-03-28 12:00:00\",\n" +
            "    \"stIndexLevel\": {\n" +
            "        \"id\": 2,\n" +
            "        \"indexLevelName\": \"Umiarkowany\"\n" +
            "    },\n" +
            "    \"stSourceDataDate\": \"2017-03-28 12:00:00\"\n" +
            "}";

    private static Index instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, Index.class);
    }

    @Test
    public void getId() {
        assertEquals(instance.getId(), 52);
    }

    @Test
    public void getStCalcDate() {
        assertEquals(instance.getStCalcDate(), "2017-03-28 12:00:00");
    }

    @Test
    public void getStIndexLevel() {
        assertNotNull(instance.getStIndexLevel());
    }

    @Test
    public void getStSourceDataDate() {
        assertEquals(instance.getStSourceDataDate(), "2017-03-28 12:00:00");
    }
}