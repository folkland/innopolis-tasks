package part01.lesson11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * -Xmx50m -XX:+UseSerialGC -XX:MaxMetaspaceSize=2m  -> java.lang.OutOfMemoryError: Metaspace
 * -Xmx50m -XX:+UseSerialGC -> java.lang.OutOfMemoryError: Java heap space
 */
public class Application {

    static Random random = new Random();
    static final int COUNT = 1_000_000_000;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            list.add(generateInt());
        }
    }

    public static int generateInt() {
        int result;
        //do something
        String user = "user";
        double d = 12387d;
        if ("user".equals(user)) {
            return random.nextInt();
        }
        return -1;
    }
}
