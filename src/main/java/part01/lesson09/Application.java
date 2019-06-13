package part01.lesson09;

/**
 * Main class
 *
 * @author folkland
 */
public class Application {

    public static void main(String[] args) {

        GenerateClassFile gcf = new GenerateClassFile();

        if (gcf.getStatus() == 0) {
            useWorker();
        }
    }

    /**
     * Using custom loader for class which implements from Worker interface
     */
    public static void useWorker() {
        try {
            ClassLoader loader = new WorkerLoader();
            Class<?> cl = loader.loadClass("part01.lesson09.SomeClass");
            Worker worker = (Worker) cl.newInstance();
            worker.doWork();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
