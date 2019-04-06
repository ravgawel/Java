package basepackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class allows you to load JSON files
 */
public class Reader {
    private IReader iReader;

    public Reader(){}


    /**
     * Method allows to read text online
     * @param url is an access url
     * @return String that contains JSON
     * @throws IOException If an input or output exception occurred
     */
    public String onlineReader(String url) throws IOException {
        iReader = new OnlineReader();
        return iReader.read(url);
    }

    /**
     * Method allows you to read file
     * @param path is an access path
     * @return String that contains JSON
     * @throws IOException If an input or output exception occurred
     */
    public String offlineReader(String path, String name) throws IOException {
        iReader = new OfflineReader();
        return iReader.read(path+name);
    }
}
