package basepackage;

import java.io.IOException;

/**
 * Classes implementing this interface should load files,
 * in particular JSON files
 */
public interface IReader {

    /**
     * @param path is an access path
     * @return String that constains JSON
     * @throws IOException If an input or output exception occurred
     */
    public String read(String path) throws IOException;
}
