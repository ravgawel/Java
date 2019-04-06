package basepackage;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Class that allows access to all parsers
 */
public class Parser {
    private static Stations stations;
    private static Sensors sensors;
    private static Datas datas;
    private static Indexes indexes;

    public Parser(){
        stations = new Stations();
        sensors = new Sensors();
        datas = new Datas();
        indexes = new Indexes();
    }

    public Parser(Reader reader){
        stations = new Stations(reader);
        sensors = new Sensors(reader);
        datas = new Datas(reader);
        indexes = new Indexes(reader);
    }

    /**
     * @return @return List of stations and informations about them
     * @throws IOException If an input or output exception occurred
     */
    public  HashMap<String, Station> stationParser() throws IOException {
        return stations.parser();
    }

    /**
     * @param stationId Identifier of the station we are interested in
     * @return sensors belonging to the station
     * @throws IOException If an input or output exception occurred
     */
    public  HashMap<Integer, Sensor> sensorParser(int stationId) throws IOException {
        return sensors.parser(stationId);
    }

    /**
     * @param id Identifier of the sensor we are interested in
     * @return @return data of sensor
     * @throws IOException If an input or output exception occurred
     */
    public Data dataParser(int id) throws IOException {
        return datas.parser(id);
    }

    /**
     * @param id Identifier of the station we are interested in
     * @return Index of the station and informations about it
     * @throws IOException If an input or output exception occurred
     */
    public Index indexParser(int id) throws IOException{
        return indexes.parser(id);
    }


    /**
     * Methods that allows to uptade cache
     * @throws IOException If an input or output exception occurred
     */
    public void updateCache() throws IOException {
        HashMap<String, Station> station = stations.onlineParser();
        HashMap<Integer, Sensor> sensor = new HashMap<>();
        for (String stationName: station.keySet()){
            Index index = indexes.onlineParser(station.get(stationName).getId());
            sensor = sensors.onlineParser(station.get(stationName).getId());
            for (Integer i : sensor.keySet()){
                Data data = datas.onlineParser(i);
            }
        }

    }


}
