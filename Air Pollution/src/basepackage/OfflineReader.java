package basepackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class allows you to read file
 */
public class OfflineReader implements IReader {
    /**
     * @param path is an access path
     * @return String that contains JSON
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public String read(String path) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(path)));
        return content;
    }
}
