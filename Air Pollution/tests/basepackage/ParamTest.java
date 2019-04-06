package basepackage;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParamTest {
    public static final String SAMPLE_RESPONSE =
            "{\n" +
            "        \"paramName\": \"pył zawieszony PM10\",\n" +
            "        \"paramFormula\": \"PM10\",\n" +
            "        \"paramCode\": \"PM10\",\n" +
            "        \"idParam\": 3\n" +
            "    }";

    private static Param instance;

    @Before
    public void parseResponse() {
        Gson gson = new Gson();
        instance = gson.fromJson(SAMPLE_RESPONSE, Param.class);
    }
    @Test
    public void getParamName() {
        assertEquals(instance.getParamName(), "pył zawieszony PM10");
    }

    @Test
    public void getParamFormula() {
        assertEquals(instance.getParamFormula(), "PM10");
    }

    @Test
    public void getParamCode() {
        assertEquals(instance.getParamCode(), "PM10");
    }

    @Test
    public void getIdParam() {
        assertEquals(instance.getIdParam(), 3);
    }
}