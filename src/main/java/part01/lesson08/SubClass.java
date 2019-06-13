package part01.lesson08;

import java.io.Serializable;

public class SubClass implements Serializable {

    int value;
    String str;
    Type type;

    public SubClass(int value, String str) {
        this.value = value;
        this.str = str;
        type = Type.SOME;
    }

    @Override
    public String toString() {
        return "SubClass{" +
                "value=" + value +
                ", str='" + str + '\'' +
                ", type=" + type +
                '}';
    }

    enum Type {
        SOME, OR, NOT;
    }
}
