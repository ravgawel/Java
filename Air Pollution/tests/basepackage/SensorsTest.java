package basepackage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SensorsTest {
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

    private static Reader mockReader;

    @Before
    public void init() throws IOException {
        mockReader = mock(Reader.class);
        when(mockReader.offlineReader(anyString(), anyString())).thenReturn(SAMPLE_RESPONSE);
        when(mockReader.onlineReader(anyString())).thenReturn(SAMPLE_RESPONSE);

    }

    @Test
    public void parser() throws IOException {
        Sensors sensors = new Sensors(mockReader);
        HashMap<Integer, Sensor> sensor = sensors.parser(14);
        assertEquals(sensor.get(92).getId(), 92);
        assertEquals(sensor.get(92).getStationId(), 14);
        assertEquals(sensor.get(92).getParam().getIdParam(), 3);
        assertEquals(sensor.get(92).getParam().getParamCode(), "PM10");
        assertEquals(sensor.get(92).getParam().getParamFormula(), "PM10");
        assertEquals(sensor.get(92).getParam().getParamName(), "pył zawieszony PM10");

        assertEquals(sensor.get(88).getId(), 88);
        assertEquals(sensor.get(88).getStationId(), 14);
        assertEquals(sensor.get(88).getParam().getIdParam(), 6);
        assertEquals(sensor.get(88).getParam().getParamCode(), "NO2");
        assertEquals(sensor.get(88).getParam().getParamFormula(), "NO2");
        assertEquals(sensor.get(88).getParam().getParamName(), "dwutlenek azotu");

    }

    @Test
    public void onlineParser() throws IOException {
        Sensors sensors = new Sensors(mockReader);
        HashMap<Integer, Sensor> sensor = sensors.onlineParser(0);

        assertEquals(sensor.get(92).getId(), 92);
        assertEquals(sensor.get(92).getStationId(), 14);
        assertEquals(sensor.get(92).getParam().getIdParam(), 3);
        assertEquals(sensor.get(92).getParam().getParamCode(), "PM10");
        assertEquals(sensor.get(92).getParam().getParamFormula(), "PM10");
        assertEquals(sensor.get(92).getParam().getParamName(), "pył zawieszony PM10");

        assertEquals(sensor.get(88).getId(), 88);
        assertEquals(sensor.get(88).getStationId(), 14);
        assertEquals(sensor.get(88).getParam().getIdParam(), 6);
        assertEquals(sensor.get(88).getParam().getParamCode(), "NO2");
        assertEquals(sensor.get(88).getParam().getParamFormula(), "NO2");
        assertEquals(sensor.get(88).getParam().getParamName(), "dwutlenek azotu");
    }
}