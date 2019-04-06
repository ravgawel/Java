package basepackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class for loading data and converting it into an object form
 */
public class Datas {
    private static final String url =  "http://api.gios.gov.pl/pjp-api/rest/data/getData/";
    private static final String path = "C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\data\\";
    private static Reader reader;
    private static Gson gson;

    public Datas(){
        reader = new Reader();
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH").create();
    }

    public Datas(Reader reader){
        this.reader = reader;
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH").create();
    }

    /**
     * Method allows you to read file and parse JSON
     * If it is nessesery sends task to onlineParser
     * @param id Identifier of the sensor we are interested in
     * @return data of sensor
     * @throws IOException If an input or output exception occurred
     */
    public Data parser(int id) throws IOException {
        Data data = new Data();
        String name = id + ".json";
        try{
            String content =  reader.offlineReader(path, name);
            data = gson.fromJson(content, Data.class);
        }catch (IOException e){
            System.out.println(id);
            data = onlineParser(id);
        }
        return data;
    }

    /**
     * Method allows you to read file online and parse JSON
     * @param id Identifier of the sensor we are interested in
     * @return data of sensor
     * @throws IOException If an input or output exception occurred
     */
    public Data onlineParser(int id) throws IOException {
        String name = id + ".json";

        String fileData = reader.onlineReader(url+id);
        Data data  = gson.fromJson(fileData, Data.class);

        Files.write(Paths.get("C:\\Users\\Rafał\\Desktop\\Projekt2\\cache\\data\\", name), fileData.getBytes());
        return data;
    }
}
