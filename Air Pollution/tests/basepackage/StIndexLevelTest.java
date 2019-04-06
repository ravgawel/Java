package basepackage;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StIndexLevelTest {
    public static final String SAMPLE_RESPONSE =
                    "    {\n" +
                    "        \"id\": 2,\n" +
                    "        \"indexLevelName\": \"Umiarkowany\"\n" +
                    "    }\n";

    private static StIndexLevel instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, StIndexLevel.class);
    }

    @Test
    public void getId(){
        assertEquals(instance.getId(), 2);
    }

    @Test
    public void getIndexLevelName() {
        assertEquals(instance.getIndexLevelName(), "Umiarkowany");
    }
}