package part01.lesson02.task02;

import java.security.SecureRandom;
import java.util.Random;

public class Application {

    static private final int N = 100;
    static private final SecureRandom RANDOM = new SecureRandom();

    public static void main(String[] args) {
        int negativeNumber = 0;
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = RANDOM.nextInt(100) - 50;
        }
        for (int k: array) {
            try {
                if (k < 0) throw new IllegalArgumentException("value is a negative");
                double q = Math.sqrt(k);
                int check = (int) q;
                if (k != 0 && Math.pow(check, 2) == k) System.out.println("k=" + k + " q=" + q);
            } catch (IllegalArgumentException e) {
                negativeNumber++;
            }
        }
        System.out.println("Count of negative numbers = " + negativeNumber);
    }
}
