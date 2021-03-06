package part01.lesson02.task02;

import java.security.SecureRandom;

public class Application {

    static private final int N = 100;
    static private final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        int[] array = createArray();
        for (int k : array) {
            double q = Math.sqrt(k);
            int check = (int) q;
            if (k != 0 && Math.pow(check, 2) == k) System.out.println("k=" + k + " q=" + q);
        }
    }

    /**
     * Create random array of int
     * @return array
     */
    public static int[] createArray() {
        int negativeNumber = 0;
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            try {
                array[i] = RANDOM.nextInt(100) - 50;
                if (array[i] < 0) throw new IllegalArgumentException("value is a negative");
            } catch (IllegalArgumentException e) {
                i--;
                negativeNumber++;
            }
        }
        System.out.println("Count of negative numbers = " + negativeNumber);
        return array;
    }
}
