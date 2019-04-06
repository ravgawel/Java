package basepackage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StationsTest {
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

    private static Reader mockReader;

    @Before
    public void init() throws IOException {
        mockReader = mock(Reader.class);
        when(mockReader.offlineReader(anyString(), anyString())).thenReturn(SAMPLE_RESPONSE);
        when(mockReader.onlineReader(anyString())).thenReturn(SAMPLE_RESPONSE);

    }

    @Test
    public void parser() throws IOException, URISyntaxException {
        Stations stations = new Stations(mockReader);
        HashMap<String, Station> station = stations.parser();

        assertEquals(station.get("Działoszyn").getId(), 14);
        assertEquals(station.get("Działoszyn").getStationName(), "Działoszyn");
        assertEquals(station.get("Działoszyn").getGegrLat(), "50.972167");
        assertEquals(station.get("Działoszyn").getGegrLon(), "14.941319");
        assertEquals(station.get("Działoszyn").getCity().getId(), 192);
        assertEquals(station.get("Działoszyn").getCity().getName(),"Działoszyn");
        assertEquals(station.get("Działoszyn").getCity().getCommune().getCommuneName(), "Bogatynia");
        assertEquals(station.get("Działoszyn").getCity().getCommune().getDistrictName(), "zgorzelecki");
        assertEquals(station.get("Działoszyn").getCity().getCommune().getProvinceName(), "DOLNOŚLĄSKIE");
        assertEquals(station.get("Działoszyn").getAddresssStreet(), null);


    }

    @Test
    public void onlineParser() throws IOException, URISyntaxException {
        Stations stations = new Stations(mockReader);
        HashMap<String, Station> station = stations.onlineParser();

        assertEquals(station.get("Działoszyn").getId(), 14);
        assertEquals(station.get("Działoszyn").getStationName(), "Działoszyn");
        assertEquals(station.get("Działoszyn").getGegrLat(), "50.972167");
        assertEquals(station.get("Działoszyn").getGegrLon(), "14.941319");
        assertEquals(station.get("Działoszyn").getCity().getId(), 192);
        assertEquals(station.get("Działoszyn").getCity().getName(),"Działoszyn");
        assertEquals(station.get("Działoszyn").getCity().getCommune().getCommuneName(), "Bogatynia");
        assertEquals(station.get("Działoszyn").getCity().getCommune().getDistrictName(), "zgorzelecki");
        assertEquals(station.get("Działoszyn").getCity().getCommune().getProvinceName(), "DOLNOŚLĄSKIE");
        assertEquals(station.get("Działoszyn").getAddresssStreet(), null);
    }
}