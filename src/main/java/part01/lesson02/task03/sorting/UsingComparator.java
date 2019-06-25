package part01.lesson02.task03.sorting;

import part01.lesson02.task03.Person;
import part01.lesson02.task03.Sex;
import part01.lesson02.task03.exception.SamePersonException;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Сортировка с использованием компараторов
 * Выделять компараторы в данной ситуации в отдельный пакет и классы не увидел смысла
 *
 * @author folkland
 */
public class UsingComparator extends SortingAbstractClass {

    @Override
    public void sort(Person[] person) {
        long beginAlghorithm = System.currentTimeMillis();
        Comparator<Person> personComparator = new PersonSexComporator().thenComparing(new PersonAgeComporator()).thenComparing(new PersonNameComporator());
        Arrays.sort(person, personComparator);
        long endAlghorithm = System.currentTimeMillis();
        setTime(endAlghorithm - beginAlghorithm);
    }
}

class PersonSexComporator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (!o1.getSex().equals(o2.getSex()) && Sex.getWOMAN().equals(o1.getSex())) {
            return 1;
        } else if (!o1.getSex().equals(o2.getSex()) && Sex.getMAN().equals(o1.getSex())) {
            return -1;
        }
        return 0;
    }
}
class PersonAgeComporator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.getAge() < o2.getAge()) {
            return 1;
        } else if (o1.getAge() > o2.getAge()) {
            return -1;
        }
        return 0;
    }
}
class PersonNameComporator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        try {
            if (o1.getName().equals(o2.getName()) && o1.getAge() == o2.getAge()) {
                throw new SamePersonException("I found same Person:" + o1.toString());
            }
        } catch (SamePersonException e) {
            System.out.println(e.getMessage());
        }
        return o1.getName().compareTo(o2.getName());
    }
}