package basepackage;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class for loading index and converting it into an object form
 */
public class Indexes {
    private static final String url =  "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/";
    private static final String path  = "C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\index\\";
    private static Reader reader;
    private static Gson gson;

    public Indexes() {
        reader = new Reader();
        gson = new Gson();
    }

    public Indexes(Reader reader) {
        this.reader = reader;
        gson = new Gson();
    }

    /**
     * Method allows you to read file and parse JSON
     * If it is nessesery sends task to onlineParser
     * @param id Identifier of the station we are interested in
     * @return Index of the station and informations about it
     * @throws IOException If an input or output exception occurred
     */
    public Index parser(int id) throws IOException {
        Index index = new Index();
        String name = id + ".json";
        try{
            String content =  reader.offlineReader(path, name);
            index = gson.fromJson(content, Index.class);
        }catch (IOException e){
            index = onlineParser(id);
        }
        return index;
    }

    /**
     * Method allows you to read file online and parse JSON
     * @param  id Identifier of the station we are interested in
     * @return Index of the station and informations about it
     * @throws IOException If an input or output exception occurred
     */
    public Index onlineParser(int id) throws IOException {
        String name = id + ".json";
        String fileData = reader.onlineReader(url+id);
        Index index  = gson.fromJson(fileData, Index.class);
        Files.write(Paths.get("C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\index\\", name), fileData.getBytes());
        return index;
    }
}
