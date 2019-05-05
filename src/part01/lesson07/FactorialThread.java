package part01.lesson07;

import java.math.BigInteger;

/**
 * Try to use Runnable interface
 *
 * @author folkland
 */
public class FactorialThread implements Runnable {

    private BigInteger result;
    private int start;
    private int finish;

    public FactorialThread(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    public BigInteger getResult() {
        return result;
    }

    @Override
    public void run() {
        Factorial factorial = new Factorial();
        result = factorial.calculateSegment(start, finish);
    }
}
