package info.qumeric.hw.task3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *  Inteface which provides serialization.
 *  It means that we can write Java object to the disk and read it from there later.
 */
public interface Serializable {
    void serialize(OutputStream out) throws IOException;
    void deserialize(InputStream in) throws IOException;
}
