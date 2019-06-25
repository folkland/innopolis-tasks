package part01.lesson05;

/**
 * Class Person have information about per person: name, age and sex
 *
 * @author folkland
 */
public class Person implements Comparable {

    private String name;
    private int age;
    private Sex sex;

    public Person(String name, int age, Sex sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public void setSex(Sex sex) {
//        this.sex = sex;
//    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Person) o).getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {return false;}
        if (this == obj) {return true;}
        Person p = (Person) obj;
        if (name.equals(p.getName()) && age == p.getAge() && sex.equals(p.getSex())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age + sex.hashCode();
    }
}
