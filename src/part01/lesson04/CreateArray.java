package part01.lesson04;

import java.security.SecureRandom;

/**
 * Class for random creating arrays for testing Generic class
 * @param <T> any Class which extends from Number class
 *
 * @author folkland
 */
public class CreateArray<T extends Number> {

    private SecureRandom random = new SecureRandom();
    private Number[] a;
    private Class<T> tClass;

    /**
     * Constructor
     * @param cl Class which array need
     */
    public CreateArray(Class<T> cl) {
        tClass = cl;
    }

    /**
     * Function which create desired class array
     * @param elementCount array size
     * @return random generated array
     */
    public T[] createArray(int elementCount) {
        a = new Number[elementCount];
        T[] array = (T[]) a;
        for (int i = 0; i < elementCount; i++) {
            T element = createElement(elementCount);
            if (checkElement(array, element)) {
                array[i] = element;
                i++;
            }
            i--;
        }
        return array;
    }

    /**
     * Create element for array, look to desired class
     * @param elementCount
     * @return
     */
    private T createElement(int elementCount) {
        if (tClass.equals(Integer.class)) {
            Integer value = random.nextInt(elementCount);
            return (T) value;
        }
        if (tClass.equals(Double.class)) {
            Double value = random.nextDouble() * elementCount;
            return (T) value;
        }
        if (tClass.equals(Float.class)) {
            Float value = random.nextFloat() * elementCount;
            return (T) value;
        }
        if (tClass.equals(Long.class)) {
            Long value = random.nextLong() * elementCount;
            return (T) value;
        }
        if (tClass.equals(Short.class)) {
            Integer i = random.nextInt(elementCount);
            Short value = i.shortValue();
            return (T) value;
        }
        if (tClass.equals(Byte.class)) {
            Integer i = random.nextInt(elementCount);
            Byte value = i.byteValue();
            return (T) value;
        }
        return null;
    }

    /**
     * Check unique element
     * @param array where check
     * @param value value which check
     * @return true - value unique, false - else
     */
    private boolean checkElement(T[] array, T value) {
        boolean notNull = true;
        for (int i = 0; i < array.length && notNull; i++) {
            if (array[i] == null) {
                notNull = false;
            }
            if (notNull && array[i].equals(value)) {
                return false;
            }
        }
        return true;
    }
}
