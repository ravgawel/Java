package basepackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class allows you to read text online
 */
public class OnlineReader implements IReader {
    /**
     * @param path is an access path
     * @return String that contains JSON
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public String read(String path) throws IOException {
        try {
            StringBuilder site = new StringBuilder();
            URL url = new URL(path);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                site.append(inputLine);
            in.close();
            return site.toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
