package part01.lesson11;

import part01.lesson09.Worker;
import part01.lesson09.WorkerLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx50m -XX:+UseSerialGC -XX:MaxMetaspaceSize=2m
 * @author folkland
 */
public class MetaspaceOutOfMemory {

    private static final int LOOP = 1_000_000_000;

    public static void main(String[] args) {
        try {
            List<Worker> workers = new ArrayList<>();
            for (int i = 0; i < LOOP; i++) {
                WorkerLoader loader = new WorkerLoader();
                Class<?> cl = loader.loadClass("part01.lesson09.SomeClass");
                Worker worker = (Worker) cl.newInstance();
                workers.add(worker);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
