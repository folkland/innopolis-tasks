package part01.lesson07;

import java.math.BigInteger;

/**
 * Class need for calculate factorial
 *
 * @author folkland
 */
public class Factorial {

    /**
     * Calculate factorial
     * @param num factorial for number
     * @return factorial
     */
    public BigInteger calculate(int num) {
        return calculateSegment(1, num);
    }

    /**
     * Multiply all number between start and finish
     * @param start start position
     * @param finish finish position
     * @return multiply number
     */
    public BigInteger calculateSegment(int start, int finish) {
        int i = start;
        BigInteger bigInteger = BigInteger.valueOf(1);
        while (i <= finish) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
            i++;
        }
        return bigInteger;
    }
}
