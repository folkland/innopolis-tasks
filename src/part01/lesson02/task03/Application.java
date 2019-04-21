package part01.lesson02.task03;

import part01.lesson02.task03.sorting.BubleSort;
import part01.lesson02.task03.sorting.QuikSort;
import part01.lesson02.task03.sorting.UsingComparator;

import java.security.SecureRandom;
import java.util.Locale;

/*
 * Получил интересные результаты, проведя небольшое количество тестов
 * Удивила скорость алгоритма быстрой сортировки
 * Компараторы работают примерно одинокаово, что для 100 элементов, что для 10000
 * Ну а пузырёк есть пузырёк =)
 */
public class Application {

    private static final int PERSON_COUNT = 10000;
    private static SecureRandom random = new SecureRandom();

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOVER = UPPER.toLowerCase(Locale.ROOT);


    /*
    Надеюсь ничего плохого, что я не вывожу в консоль сами отсортированные массивы, лично проверял для массивов из 15 элементов
    Больше интересно было посмотреть на результаты по времени
    Javadoc на русском языке, в дальнейшем буду стараться на английском
     */
    public static void main(String[] args) {
        Person[] personsBuble = createPersonArrays();
        System.out.println("Before Sorting");
//        showPersons(personsBuble);
        Person[] personsQuik = personsBuble;
        Person[] personsComparator = personsBuble;
        BubleSort bubleSort = new BubleSort();
        QuikSort quikSort = new QuikSort();
        UsingComparator usingComparator = new UsingComparator();
        bubleSort.sort(personsBuble);
        quikSort.sort(personsQuik);
        usingComparator.sort(personsComparator);
        System.out.println("Время сортировки пузырьком: " + bubleSort.time());
//        showPersons(personsBuble);
//        showPersons(personsQuik);
        System.out.println("Время быстрой сортировки: " + quikSort.time());
//        showPersons(personsComparator);
        System.out.println("Время сортировки компароторами: " + usingComparator.time());
    }

    private static Person createPerson() {
        int age = random.nextInt(100);
        String sex;
        if (random.nextInt(2) == 1) {
            sex = Sex.getWOMAN();
        } else {
            sex = Sex.getMAN();
        }
        String name = createName();
        return new Person(name, age, sex);
    }

    private static Person[] createPersonArrays() {
        Person[] persons = new Person[PERSON_COUNT];
        for (int i = 0; i < PERSON_COUNT; i++) {
            persons[i] = createPerson();
        }
        return persons;
    }

    private static String createName() {
        int lengthName = random.nextInt(10) + 3;
        char[] nameInChar = new char[lengthName];
        nameInChar[0] = UPPER.charAt(random.nextInt(UPPER.length()));
        for (int i = 1; i < lengthName; i++) {
            nameInChar[i] = LOVER.charAt(random.nextInt(LOVER.length()));
        }
        return String.valueOf(nameInChar);
    }

    private static void showPersons(Person[] persons) {
        for (Person person: persons) {
            System.out.println(person.toString());
        }
    }
}
