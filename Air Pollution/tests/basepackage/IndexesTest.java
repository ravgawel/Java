package basepackage;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static basepackage.Main.stringToDate;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IndexesTest {
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

    private static Reader mockReader;

    @Before
    public void init() throws IOException {
        mockReader = mock(Reader.class);
        when(mockReader.offlineReader(anyString(), anyString())).thenReturn(SAMPLE_RESPONSE);
        when(mockReader.onlineReader(anyString())).thenReturn(SAMPLE_RESPONSE);

    }
    @Test
    public void parser() throws Exception {
        Indexes indexes = new Indexes(mockReader);
        Index index = indexes.parser(52);

        assertEquals(index.getId(), 52);
        assertEquals(index.getStCalcDate(), "2017-03-28 12:00:00");
        assertEquals(index.getStIndexLevel().getId(), 2);
        assertEquals(index.getStIndexLevel().getIndexLevelName(), "Umiarkowany");
        assertEquals(index.getStSourceDataDate(), "2017-03-28 12:00:00");
    }

    @Test
    public void onlineParser() throws Exception {
        Indexes indexes = new Indexes(mockReader);
        Index index = indexes.onlineParser(0);

        assertEquals(index.getId(), 52);
        assertEquals(index.getStCalcDate(), "2017-03-28 12:00:00");
        assertEquals(index.getStIndexLevel().getId(), 2);
        assertEquals(index.getStIndexLevel().getIndexLevelName(), "Umiarkowany");
        assertEquals(index.getStSourceDataDate(), "2017-03-28 12:00:00");

    }
}