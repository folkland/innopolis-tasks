package part01.lesson08;

import java.io.Serializable;
import java.util.Arrays;

/**
 * CLass with some field for serialization
 *
 * @author folkland
 */
public class SomeClass implements Serializable {

    transient int num = 123456543;
    String str;
    double dbl;
    boolean isSome;
    byte[] fewByte;
    String[] arr = {"asdas", "sadas"};
    int[] ints = {4,5,234};
    SubClass subClass;

    public SomeClass(String str, double dbl, boolean isSome, byte[] fewByte) {
        this.str = str;
        this.dbl = dbl;
        this.isSome = isSome;
        this.fewByte = fewByte;
        subClass = new SubClass(num, str);
    }

    @Override
    public String toString() {
        return "SomeClass{ num="  + num +
                ", str='" + str + '\'' +
                ", dbl=" + dbl +
                ", isSome=" + isSome +
                ", fewByte=" + Arrays.toString(fewByte) +
                ", subClass=" + subClass.toString() +
                '}';
    }
}
