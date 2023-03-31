package DataAccess;

import BusinessLogic.BaseProduct;
import BusinessLogic.CompositeProduct;
import BusinessLogic.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Serializator {
    public static void writeObject(Object v, String destinationFile) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destinationFile))) {
            os.writeObject(v);
            os.flush();
        }
    }

    public static Object deserialization(String file) {
        Object object = null;
        try {
            FileInputStream myFile = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(myFile);
            object = ois.readObject();
            ois.close();
            myFile.close();
        } catch (FileNotFoundException e) {

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return object;
    }

    public static void serialization(Object object, String fileDestination) {

        if (object instanceof List) {
            List<Object> objects = (ArrayList<Object>) object;
            List<Object> list = objects.stream().distinct().collect(Collectors.toList());
            try {
                writeObject(list, fileDestination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                writeObject(object, fileDestination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
