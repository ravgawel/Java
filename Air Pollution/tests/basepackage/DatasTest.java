package basepackage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;

import static basepackage.Main.stringToDate;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatasTest {
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

    private static Reader mockReader;

    @Before
    public void init() throws IOException {
        mockReader = mock(Reader.class);
        when(mockReader.offlineReader(anyString(), anyString())).thenReturn(SAMPLE_RESPONSE);
        when(mockReader.onlineReader(anyString())).thenReturn(SAMPLE_RESPONSE);

    }
    @Test
    public void parser() throws Exception {
        Datas datas = new Datas(mockReader);
        Data data = datas.parser(92);

        assertEquals(data.getKey(), "PM10");
        assertEquals(data.getValues()[0].getValue(), 30.3018, 0.1);
        assertEquals(data.getValues()[0].getDate(), stringToDate("2017-03-28 11:00:00"));
        assertEquals(data.getValues()[1].getValue(), 27.5946, 0.1);
        assertEquals(data.getValues()[1].getDate(), stringToDate("2017-03-28 12:00:00"));
    }

    @Test
    public void onlineParser() throws Exception {
        Datas datas = new Datas(mockReader);
        Data data = datas.onlineParser(0);

        assertEquals(data.getKey(), "PM10");
        assertEquals(data.getValues()[0].getValue(), 30.3018, 0.1);
        assertEquals(data.getValues()[0].getDate(), stringToDate("2017-03-28 11:00:00"));
        assertEquals(data.getValues()[1].getValue(), 27.5946, 0.1);
        assertEquals(data.getValues()[1].getDate(), stringToDate("2017-03-28 12:00:00"));
    }
}