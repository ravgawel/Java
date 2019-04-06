package basepackage;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static basepackage.Main.stringToDate;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeaturesTest {
    private static Parser mockParser;
    private static Reader mockReader;
    private static Stations stations;
    private static Reader mockReader2;
    private static Sensors sensors;
    private static Reader mockReader3;
    private static Datas datas;
    private static Reader mockReader4;
    private static Indexes indexs;
    private static HashMap<String, Station> station;
    private static HashMap<Integer, Sensor> sensor;
    private static Index index;
    private static Data data;

    @Before
    public void init() throws IOException, URISyntaxException {
        mockParser = mock(Parser.class);


        mockReader = mock(Reader.class);
        stations= new Stations(mockReader);
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Rafał\\IdeaProjects\\Project2\\tests\\basepackage", "findall.json")));
        when(mockReader.offlineReader(anyString(), anyString())).thenReturn(content);
        station = stations.parser();
        when(mockParser.stationParser()).thenReturn(station);
        mockReader2 = mock(Reader.class);
        indexs= new Indexes(mockReader2);

        String content2[] = new String[3];
        int k = 0;
        for (String x : station.keySet()){
            String name = station.get(x).getId() + ".json";
            content2[k] = new String(Files.readAllBytes(Paths.get("C:\\Users\\Rafał\\IdeaProjects\\Project2\\tests\\basepackage\\index", name)));
            when(mockReader.offlineReader(anyString(), anyString())).thenReturn(content2[k]);
            index = indexs.parser(station.get(x).getId());
            k++;
        }

        mockReader3 = mock(Reader.class);
        sensors = new Sensors(mockReader3);
        String content3[] = new String[3];
        k = 0;
        for (String x : station.keySet()){
            String name = station.get(x).getId() + ".json";
            content3[k] = new String(Files.readAllBytes(Paths.get("C:\\Users\\Rafał\\IdeaProjects\\Project2\\tests\\basepackage\\sensors", name)));
            when(mockReader3.offlineReader(anyString(), anyString())).thenReturn(content3[k]);
            sensor = sensors.parser(station.get(x).getId());
            k++;
        }

        mockReader4 = mock(Reader.class);
        datas = new Datas(mockReader4);
        String content4[] = new String[17];
        k = 0;
        for (Integer x : sensor.keySet()){
            String name = x + ".json";
            content4[k] = new String(Files.readAllBytes(Paths.get("C:\\Users\\Rafał\\IdeaProjects\\Project2\\tests\\basepackage\\data", name)));
            when(mockReader3.offlineReader(anyString(), anyString())).thenReturn(content4[k]);
            data = datas.parser(sensor.get(x).getId());
            k++;
        }

    }
    @Test
    public void func1() throws Exception {
        Features features = new Features();

        assertEquals(features.func1("Kraków, ul. Bulwarowa"), "Zły");
        assertEquals(features.func1("Kraków, Aleja Krasińskiego"), "Zły");
        assertEquals(features.func1("Kraków, ul. Bujaka"), "Bardzo zły");


    }

    @Test
    public void func2() throws Exception {
        Features features = new Features();

        assertEquals(features.func2("Kraków, ul. Bulwarowa", stringToDate("2019-01-19 5:00:00"), "PM10"), 18.8255, 0.1);
        assertEquals(features.func2("Kraków, ul. Bulwarowa", stringToDate("2019-01-19 7:00:00"), "CO"), 612.27, 0.1);
        assertEquals(features.func2("Kraków, ul. Bulwarowa", stringToDate("2019-01-19 8:00:00"), "NO2"), 34.7511, 0.1);
        assertEquals(features.func2("Kraków, ul. Bulwarowa", stringToDate("2019-01-19 8:00:00"), "PM10"), 30.2667, 0.1);
        assertEquals(features.func2("Kraków, ul. Bulwarowa", stringToDate("2019-01-19 8:00:00"), "PM2.5"), 23.2, 0.1);
        assertEquals(features.func2("Kraków, ul. Bulwarowa", stringToDate("2019-01-19 8:00:00"), "C6H6"), 1.32732, 0.1);
    }

    @Test
    public void func3() throws Exception {
        Features features = new Features();
        assertEquals(features.func3("Kraków, ul. Bulwarowa", stringToDate("2019-01-19 4:00:00"), stringToDate("2019-01-19 5:00:00"), "PM10"), 19.01869, 0.1);

    }

    @Test
    public void func4() throws Exception {
        Features features = new Features();

        ArrayList<String> station = new ArrayList<>();
        station.add("Kraków, ul. Bujaka");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 00:00:00")), "PM10");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 15:00:00")), "PM10");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 21:00:00")), "PM10");

        station.add("Kraków, Aleja Krasińskiego");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 00:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 15:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 21:00:00")), "CO");

        station.add("Kraków, ul. Bulwarowa");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 00:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 15:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 21:00:00")), "CO");


        station = new ArrayList<>();
        station.add("Kraków, Aleja Krasińskiego");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 00:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 15:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 21:00:00")), "CO");

        station = new ArrayList<>();
        station.add("Kraków, ul. Bulwarowa");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 00:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 15:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 21:00:00")), "CO");

        station.add("Kraków, ul. Bujaka");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 00:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 15:00:00")), "CO");
        assertEquals(features.func4(station,  stringToDate("2019-01-19 21:00:00")), "CO");
    }

    @Test
    public void func5() throws Exception {
        Features features = new Features();

        assertEquals(features.func5(stringToDate("2019-01-19 06:00:00")), "C6H6");
        assertEquals(features.func5(stringToDate("2019-01-19 12:00:00")), "C6H6");
        assertEquals(features.func5(stringToDate("2019-01-20 00:00:00")), "C6H6");
    }

    @Test
    public void func6() throws Exception {
        Features features = new Features();

        LinkedHashMap<String, Float> wyniki = features.func6("Kraków, ul. Bujaka", stringToDate("2019-01-20 00:00:00"));
        assertTrue(wyniki.keySet().contains("C6H6"));
        assertTrue(wyniki.keySet().contains("PM2.5"));
        assertTrue(wyniki.keySet().contains("PM10"));
        assertEquals(wyniki.get("C6H6"), 10.3573, 0.1);
        assertEquals(wyniki.get("PM2.5"), 95.425, 0.1);
        assertEquals(wyniki.get("PM10"), 102.97, 0.1);
    }

}