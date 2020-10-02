package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {
    public static void writeToFile(String data, String path) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }
}
