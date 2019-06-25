package part01.lesson08;

import java.io.*;

/**
 * Main class
 *
 * @author folkland
 */
public class Application {

    private static final String PATH = "resource/lesson08/serialize.bin";

    public static void main(String[] args) {
        SomeClass someClass = new SomeClass("hmmm", 15632d, true, "dslkgjsd".getBytes());
        serialize(someClass, PATH);
        SomeClass res = (SomeClass) deSerialize(PATH);
        System.out.println(res);
    }

    /**
     * Serialization method
     * @param object object for serialization
     * @param file file there must be serialization
     */
    private static void serialize(Object object, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialization object from file
     * @param file there serialization object
     * @return object
     */
    private static Object deSerialize(String file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
