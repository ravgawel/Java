package basepackage;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * A class for loading sensors and converting it into an object form
 */
public class Sensors {
    private static final String url =  "http://api.gios.gov.pl/pjp-api/rest/station/sensors/";
    private static final String path = "C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\sensors\\";
    private static Reader reader;
    private static Gson gson;

    public Sensors() {
        reader = new Reader();
        gson = new Gson();
    }

    public Sensors(Reader reader) {
        this.reader = reader;
        gson = new Gson();
    }

    /**
     * Method allows you to read file and parse JSON
     * If it is nessesery sends task to onlineParser
     * @param stationId Identifier of the station we are interested in
     * @return sensors belonging to the station
     * @throws IOException If an input or output exception occurred
     */
    public HashMap<Integer, Sensor> parser(int stationId) throws IOException {
        HashMap<Integer, Sensor> sensors = new HashMap<>();
        String name = stationId + ".json";
        try{
            String content =  reader.offlineReader(path, name);

            Sensor[] lista = gson.fromJson(content, Sensor[].class);
            for (Sensor a: lista) sensors.put(a.getId(), a);
        }catch (IOException e){
            sensors = onlineParser(stationId);
        }
        return sensors;

    }

    /**
     * Method allows you to read file online and parse JSON
     * @param stationId Identifier of the station we are interested in
     * @return sensors belonging to the station
     * @throws IOException If an input or output exception occurred
     */
    public HashMap<Integer, Sensor> onlineParser(int stationId) throws IOException{
        String name = stationId + ".json";
        String fileData = reader.onlineReader(url+stationId);
        Sensor[] lista = gson.fromJson(fileData, Sensor[].class);
        Files.write(Paths.get("C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\sensors\\", name), fileData.getBytes());
        HashMap<Integer, Sensor> sensors = new HashMap<>();
        for (Sensor a: lista) sensors.put(a.getId(), a);
        return sensors;
    }
}
