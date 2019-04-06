package basepackage;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * A class for loading station and converting it into an object form
 */
public class Stations {
    private static final String url = "http://api.gios.gov.pl/pjp-api/rest/station/findAll;";
    private static final String path = "C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\findall.json";
    private static Reader reader;
    private static Gson gson;

    public Stations() {
        reader = new Reader();
        gson = new Gson();
    }

    public Stations(Reader reader) {
        this.reader = reader;
        gson = new Gson();
    }

    /**
     * Method allows you to read file and parse JSON
     * If it is nessesery sends task to onlineParser
     * @return List of stations and informations about them
     * @throws IOException If an input or output exception occurred
     */
    public HashMap<String, Station> parser() throws IOException {
        HashMap<String, Station> stations = new HashMap<>();
        try{
            String content =  reader.offlineReader(path, "");
            Station[] lista = gson.fromJson(content, Station[].class);
            for (Station a: lista) stations.put(a.getStationName(), a);
        }catch (IOException e){
            stations = onlineParser();
        }
        return stations;
    }

    /**
     * Method allows you to read file online and parse JSON
     * @return List of stations and informations about them
     * @throws IOException If an input or output exception occurred
     */
    public HashMap<String, Station> onlineParser() throws IOException {
        String fileData = reader.onlineReader(url);
        Station[] lista = gson.fromJson(fileData, Station[].class);
        Files.write(Paths.get("C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\findall.json"), fileData.getBytes());
        HashMap<String, Station> stations = new HashMap<>();
        for (Station a: lista) stations.put(a.getStationName(), a);
        return stations;

    }
}
