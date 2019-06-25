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

    /**
     * Using bubble sort
     * @param person array which need sort
     */
    @Override
    public void sort(Person[] person) {
        long startAlghorithm = System.currentTimeMillis();
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < person.length - 1; i++) {
                int j = i + 1;
                if (person[i].compareTo(person[j]) == 1) {
                    Person tmpPerson = person[i];
                    person[i] = person[j];
                    person[j] = tmpPerson;
                    isSorted = false;
                }
            }
        }
        long endAlghorithm = System.currentTimeMillis();
        setTime(endAlghorithm - startAlghorithm);
    }
}
