package part01.lesson02.task03.sorting;

import part01.lesson02.task03.Person;
import part01.lesson02.task03.Sex;
import part01.lesson02.task03.exception.SamePersonException;

/**
 * Быстрая сортировка
 *
 * @author folkland
 */
public class QuikSort extends SortingAbstractClass {

    @Override
    public void sort(Person[] person) {
        long beginAlghorithm = System.currentTimeMillis();
        int start = 0;
        int end = person.length - 1;
        quikSorting(person, start, end);
        long endAlghorithm = System.currentTimeMillis();
        setTime(endAlghorithm - beginAlghorithm);
    }

    /**
     * Рукурсионная функция для алгоритма быстрой сортировки
     *
     * @param person массив для сортировки
     * @param start  позиция с которой начинать сортировку
     * @param end    позиция на которой заканчивать сортировку
     */
    public void quikSorting(Person[] person, int start, int end) {
        if (end <= start) {
            return;
        }
        int center = start + (end - start) / 2;
        int i = start, j = end;
        while (i < j) {
            while (i < center && !comparisonPerson(person[i], person[center])) {
                i++;
            }
            while (j > center && !comparisonPerson(person[center], person[j])) {
                j--;
            }
            if (i < j) {
                Person tmpPerson = person[i];
                person[i] = person[j];
                person[j] = tmpPerson;
                if (i == center) {
                    center = j;
                } else if (j == center) {
                    center = i;
                }
            }
        }
        if (start < j) {
            quikSorting(person, start, center);
        }
        if (end > i) {
            quikSorting(person, center + 1, end);
        }
    }

    /**
     * Сравнивает два объекта Person и говорит, нужно ли их менять
     *
     * @param firstPerson  объект, который лежит левее в массиве
     * @param secondPerson объект, который лежит правее в массиве
     * @return true - нужно менять местами, false - не нужно
     */
    private boolean comparisonPerson(Person firstPerson, Person secondPerson) {
        if (!firstPerson.getSex().equals(secondPerson.getSex()) && Sex.getWOMAN().equals(firstPerson.getSex())) {
            return true;
        } else if (firstPerson.getAge() < secondPerson.getAge()) {
            return true;
        } else if (firstPerson.getAge() == secondPerson.getAge()) {
            int check = firstPerson.getName().compareTo(secondPerson.getName());
            if (check >= 0) {
                try {
                    if (check == 0) {
                        throw new SamePersonException("I found same Person:" + firstPerson.toString());
                    } else {
                        return true;
                    }
                } catch (SamePersonException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return false;
    }
}
