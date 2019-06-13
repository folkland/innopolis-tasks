package part01.lesson02.task03;

import part01.lesson02.task03.exception.SamePersonException;

/**
 * Class contain information about person
 *
 * @author folkland
 */
public class Person implements Comparable {

    private String name;
    private int age;
    private String sex;

    public Person(String name, int age, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return name + " " + age + " " + sex;
    }

    /**
     * Сравнивает два объекта Person и говорит, нужно ли их менять
     *
     * @param object объект, который лежит правее в массиве
     * @return 1 - нужно менять местами, 2 - не нужно менять
     */
    @Override
    public int compareTo(Object object) {
        Person o = (Person) object;
        if (!this.getSex().equals(o.getSex())) {
            if (Sex.getWOMAN().equals(this.getSex())) {
                return 1;
            }
        } else if (this.getAge() < o.getAge()) {
            return 1;
        } else if (this.getAge() == o.getAge()) {
            int check = this.getName().compareTo(o.getName());
            if (check >= 0) {
                try {
                    if (check == 0) {
                        throw new SamePersonException("I found same Person:" + this.toString());
                    } else {
                        return 1;
                    }
                } catch (SamePersonException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return -1;
    }
}
