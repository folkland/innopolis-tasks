package part01.lesson07;

import java.security.SecureRandom;

/**
 * Main class
 *
 * @author folkland
 */
public class Application {

    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        SomeVersionOfCalculate some = new SomeVersionOfCalculate(factorial);
        int[] array = createArray(1000);
        System.out.println("Time without Threads: " + some.withoutThreads(array));

        System.out.println("Time with Threads: " + some.withThreads(array));

        System.out.println("Time with divide number to 2 threads: " + some.withDivideCalculating(array));

        System.out.println("Time with Threads pool(fork): " + some.withForkPool(array));

        System.out.println("Time with Threads pool(dynamic): " + some.withDynamicPool(array));

        System.out.println("Time with Threads pool(fixed 2): " + some.withFixedPool(array, 2));

        System.out.println("Time with Threads pool(fixed 4): " + some.withFixedPool(array, 4));

        System.out.println("Time with Threads pool(fixed 8): " + some.withFixedPool(array, 8));

        System.out.println("Time with combine pool(fork) and divide: " + some.combineForkPool(array));

        System.out.println("Time with combine pool(dynamic) and divide:: " + some.combineDynamicPool(array));

        System.out.println("Time with combine pool(fixed 2) and divide:: " + some.combineFixedPool(array, 2));

        System.out.println("Time with combine pool(fixed 4) and divide:: " + some.combineFixedPool(array, 4));

        System.out.println("Time with combine pool(fixed 8) and divide:: " + some.combineFixedPool(array, 8));
    }

    /**
     * method for generate array
     * @param size
     * @return
     */
    private static int[] createArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = 20 + random.nextInt(1000);
        }
        return result;
    }
}
