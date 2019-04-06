package basepackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import static basepackage.Main.stringToDate;
import static org.junit.Assert.*;

public class ValuesTest {
    public static final String SAMPLE_RESPONSE =
                    "    [{\n" +
                    "        \"date\": \"2017-03-28 11:00:00\",\n" +
                    "        \"value\": 30.3018\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"date\": \"2017-03-28 12:00:00\",\n" +
                    "        \"value\": 27.5946\n" +
                    "    }]";


    private static Values[] instance;

    @Before
    public void parseResponse() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH").create();
        instance = gson.fromJson(SAMPLE_RESPONSE, Values[].class);
    }
    @Test
    public void getDate() throws Exception {
        assertEquals(instance[0].getDate(), stringToDate("2017-03-28 11:00:00"));
        assertEquals(instance[1].getDate(), stringToDate("2017-03-28 12:00:00"));
    }

    @Test
    public void getValue() {
        assertEquals(instance[0].getValue(), 30.3018, 0.1);
        assertEquals(instance[1].getValue(), 27.5946, 0.1);
    }
}