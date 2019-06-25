package part01.lesson04;

import java.util.*;

/**
 * Class for test generics, Parent class
 * Have universal methods
 * @param <T> Any class
 *
 * @author folkland
 */
public class ObjectBox <T> {

    public List<T> list = new LinkedList<>();

    /**
     * Add new object to collection
     * @param object any object
     */
    public void addObject(T object) {
        list.add(object);
    }

    /**
     * Remove object from collection
     * @param object which object
     * @return true - success, false - else
     */
    public boolean deleteObject(T object) {
        return list.remove(object);
    }

    /**
     * Modifies existing element
     * @param index index in collection
     * @param object modifies to
     */
    public void modifiesValue(int index, T object) {
        list.set(index, object);
    }

    /**
     * Get element from collection
     * @param index index of element
     * @return element
     */
    public T getValue(int index) {
        return list.get(index);
    }

    /**
     * Write to string all collection elements
     * @return String with all elements
     */
    public String dump() {
        String allElementInCollection = "";
        for (T object: list) {
            allElementInCollection = allElementInCollection + object + " ";
        }
        return allElementInCollection;
    }
}
