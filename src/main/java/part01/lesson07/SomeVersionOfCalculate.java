package part01.lesson07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Class store all version of run calculate factorial for array of number
 *
 * @author folkland
 */
public class SomeVersionOfCalculate {
    private Factorial factorial;

    public SomeVersionOfCalculate(Factorial factorial) {
        this.factorial = factorial;
    }

    /**
     * Calculate factorial for array without multithreading
     * @param array
     * @return working time
     */
    public long withoutThreads(int[] array) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            factorial.calculate(array[i]);
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Calculate factorial for array with multithreading, used Thread class
     * @param array
     * @return working time
     */
    public long withThreads(int[] array) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            final int ai = i;
            new Thread(()->{
                factorial.calculate(array[ai]);
            }, "Thread"+ai).start();
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Calculate factorial for array with multithreading, using pool
     * Universal method
     * @param array
     * @return working time
     */
    private long withPool(int[] array, ExecutorService executorService) {
        long startTime = System.currentTimeMillis();
        List<Future<BigInteger>> futures = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            final int j = i;
            futures.add(CompletableFuture.supplyAsync(
                    () -> factorial.calculate(array[j]),
                    executorService
            ));
        }
        try {
            for (Future future: futures) {
                future.get();
            }
        } catch (ExecutionException e) {
            System.out.println("Error then execute future");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Error then interrupted");
            e.printStackTrace();
        }
        executorService.shutdown();
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Calculate factorial for array with multithreading, using fork pool
     * @param array
     * @return working time
     */
    public long withForkPool(int[] array) {
        ExecutorService service = Executors.newWorkStealingPool();
        return withPool(array, service);
    }

    /**
     * Calculate factorial for array with multithreading, using dynamic pool
     * @param array
     * @return working time
     */
    public long withDynamicPool(int[] array) {
        ExecutorService service = Executors.newCachedThreadPool();
        return withPool(array, service);
    }

    /**
     * Calculate factorial for array with multithreading, using fixed pool
     * @param array
     * @return working time
     */
    public long withFixedPool(int[] array, int threadCount) {
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        return withPool(array, service);
    }

    /**
     * Calculating with using dividing number to 2 threads
     * @param array
     * @return
     */
    public long withDivideCalculating(int[] array) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            divideCalculating(array[i]);
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Divide calculating in 2 threads
     * @param number
     * @return
     */
    private BigInteger divideCalculating(int number) {
        int medium = number / 2;
        FactorialThread thread1 = new FactorialThread(1, medium);
        FactorialThread thread2 = new FactorialThread(medium + 1, number);
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(thread1));
        threads.add(new Thread(thread2));
        threads.parallelStream().forEach(Thread::start);
        try {
            for (Thread thread: threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return thread1.getResult().multiply(thread2.getResult());
    }

    /**
     * Combine pool and divide
     * @param array
     * @return
     */
    private long combinePoolAndDivide(int[] array, ExecutorService executorService) {
        long startTime = System.currentTimeMillis();
        List<Future<BigInteger>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < array.length; i++) {
                final int j = array[i];
                futures.add(CompletableFuture.supplyAsync(
                        () -> divideCalculating(j),
                        executorService
                ));
            }
            for (Future future: futures) {
                future.get();
            }
        } catch (ExecutionException e) {
            System.out.println("Error then execute future");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Error then interrupted");
            e.printStackTrace();
        }
        executorService.shutdown();
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Calculate factorial for array with multithreading, using fork pool and dividing
     * @param array
     * @return working time
     */
    public long combineForkPool(int[] array) {
        ExecutorService service = Executors.newWorkStealingPool();
        return combinePoolAndDivide(array, service);
    }

    /**
     * Calculate factorial for array with multithreading, using dynamic pool and divide
     * @param array
     * @return working time
     */
    public long combineDynamicPool(int[] array) {
        ExecutorService service = Executors.newCachedThreadPool();
        return combinePoolAndDivide(array, service);
    }

    /**
     * Calculate factorial for array with multithreading, using fixed pool and divide
     * @param array
     * @return working time
     */
    public long combineFixedPool(int[] array, int threadCount) {
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        return combinePoolAndDivide(array, service);
    }
}
