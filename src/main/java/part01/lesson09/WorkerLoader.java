package part01.lesson09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Custom ClassLoader for load SomeClass which implements from Worker
 *
 * @author folkland
 */
public class WorkerLoader extends ClassLoader {

    private final String PATH = "resource/lesson09/SomeClass.class";

    /**
     * Search class in resource directory
     * @param name name of class
     * @return class
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("Take our compiled class");
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(PATH));
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    /**
     * Catch SomeClass definition
     * @param name name of class
     * @return class
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("part01.lesson09.SomeClass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }
}
