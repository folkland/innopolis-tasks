package part01.lesson04;

/**
 * Class make some arithmetic operation under collection elements
 * @param <T> Class extends Number
 *
 * @author folkland
 */
public class MathBox <T extends Number> extends ObjectBox <T> {

    /**
     * Constructor
     * @param array Number extends array for initialize
     */
    public MathBox(T[] array) {
        for (T object: array) {
            addObject(object);
        }
    }

    /**
     * Summarizes all collection elements
     * @return total in Double
     */
    public Double summator() {
        Double result = 0d;
        for (T obj: list) {
            result = result + obj.doubleValue();
        }
        return result;
    }

    /**
     * Divide all elements of collection
     * @param value divider
     */
    public void splitter(T value) {
        for (int i = 0; i < list.size(); i++) {
            Double temp = list.get(i).doubleValue() / value.doubleValue();
            modifiesValue(i, (T) temp);
        }
    }

    /**
     * Remove Integer value from collection
     * @param value value for remove
     * @return true - remove success, false - else
     */
    public boolean checkInteger(T value) {
        if (value instanceof Integer) {
            return deleteObject(value);
        }
        return false;
    }

    @Override
    public String toString() {
        return dump();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        MathBox temp = (MathBox) obj;
        if (temp.list.size() != this.list.size()) {
            return false;
        }
        boolean isEquals = true;
        for (int i = 0; i < temp.list.size() && isEquals; i++) {
            if (!this.list.get(i).equals(temp.list.get(i))) {
                isEquals = false;
            }
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (T elem: list) {
            hash = hash + elem.intValue();
        }
        return hash;
    }
}
