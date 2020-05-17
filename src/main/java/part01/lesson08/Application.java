package part01.lesson08;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Main class
 *
 * @author folkland
 */
public class Application {

    private static final String PATH = "resource/lesson08/serialize.json";

    public static void main(String[] args) {

        SomeClass someClass = new SomeClass("hmmm", 15632d, true, "dslkgjsd".getBytes());
//        SubClass subClass = new SubClass(103, "dasdas");
        serialize(someClass, PATH);
        System.out.println("done");
//        SomeClass res = (SomeClass) deSerialize(PATH);
//        System.out.println(res);
    }

    /**
     * Serialization method
     *
     * @param object object for serialization
     * @param file   file there must be serialization
     */
    private static void serialize(Object object, String file) {
        String builder = serializeClassToJson(object);
        System.out.println(builder);
        try (OutputStreamWriter writer = new FileWriter(file)) {
            writer.write(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Arrays.stream(classes).forEach(System.out::println);
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
//            oos.writeObject(object);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static String serializeClassToJson(Object object) {
        if (object.getClass().isEnum()) {
            return object.toString() + ",\n";
        }
        StringBuilder builder = new StringBuilder();
        if (object.getClass().isArray()) {
            if (object.getClass().getComponentType().isPrimitive()) {
                builder.append("{\nclass: " + object.getClass().getComponentType() + ",\ndata:");
                builder.append("[\n");
                for (int i = 0; i < Array.getLength(object); i++) {
                    builder.append(Array.get(object, i) + ",\n");
                }
                builder.append("]\n},\n");
                return builder.toString();
            }
            Object[] arrs = (Object[]) object;
            builder.append("[\n");
            for (Object arr: arrs) {
                if (arr instanceof String) builder.append(arr.toString() + ",\n");
                else builder.append(serializeClassToJson(arr));
            }
            builder.append("],\n");
            return builder.toString();
        }
        builder.append("{\n");
        builder.append("class: " + object.getClass().getSimpleName() + ",\n");
        builder.append("fields: {\n");
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getType().isPrimitive() || field.get(object) instanceof String)
                    builder.append(field.getName() + ": " + field.get(object).toString() + ",\n");
                else
                    builder.append(field.getName() + ": " + serializeClassToJson(field.get(object)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        builder.append("}\n}\n");
        return builder.toString();
    }

    /**
     * Deserialization object from file
     *
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
