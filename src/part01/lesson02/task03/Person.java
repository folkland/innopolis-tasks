package part01.lesson02.task03;

public class Person {

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
}
