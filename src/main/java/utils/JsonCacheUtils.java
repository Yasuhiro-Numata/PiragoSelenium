package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JsonCacheUtils {
    private BufferedReader reader = null;

    private static JsonCacheUtils instance;
    private static Gson gson;

    public static JsonCacheUtils get() {
        if (instance == null)
            instance = new JsonCacheUtils();
        return instance;
    }

    private JsonCacheUtils() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public synchronized <T> T getResult(String file, Class<T> clazz) {
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
//        } catch (FileNotFoundException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (reader == null) {
            return null;
        } else {
            try {
                return gson.fromJson(reader, clazz);
            } catch (Exception ex) {
                return null;
            }
        }
    }
}
