package part01.lesson02.task03.sorting;

import part01.lesson02.task03.Person;
import part01.lesson02.task03.Sex;
import part01.lesson02.task03.exception.SamePersonException;

/**
 * Сортировка пузырьком
 *
 * @author folkland
 */
public class BubleSort extends SortingAbstractClass {

    @Override
    public void sort(Person[] person) {
        long startAlghorithm = System.currentTimeMillis();
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < person.length - 1; i++) {
                int j = i + 1;
                if (!person[i].getSex().equals(person[j].getSex())) {
                    if (Sex.getWOMAN().equals(person[i].getSex())) {
                        Person tmpPerson = person[i];
                        person[i] = person[j];
                        person[j] = tmpPerson;
                        isSorted = false;
                    }
                } else if (person[i].getAge() < person[j].getAge()) {
                    Person tmpPerson = person[i];
                    person[i] = person[j];
                    person[j] = tmpPerson;
                    isSorted = false;
                } else if (person[i].getAge() == person[j].getAge()) {
                    int check =person[i].getName().compareTo(person[j].getName());
                    if ( check >= 0) {
                        try {
                            if (check == 0) {
                                throw new SamePersonException("I found same Person:" + person[i].toString());
                            } else {
                                Person tmpPerson = person[i];
                                person[i] = person[j];
                                person[j] = tmpPerson;
                                isSorted = false;
                            }
                        } catch (SamePersonException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
        long endAlghorithm = System.currentTimeMillis();
        setTime(endAlghorithm - startAlghorithm);
    }
}
