package part01.lesson05;

import java.security.SecureRandom;
import java.util.Locale;

public class GenerateAnimalAndPerson {

    private final int PERSON_COUNT;
    private final int ANIMAL_COUNT;
    private final int PERSON_NAME = 12;
    private final int ANIMAL_NAME = 6;
    private static SecureRandom random = new SecureRandom();

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOVER = UPPER.toLowerCase(Locale.ROOT);

    public GenerateAnimalAndPerson(int personCount, int animalCount) {
        PERSON_COUNT = personCount;
        ANIMAL_COUNT = animalCount;
    }

    /**
     * Create one random Animal
     * @param index identifier for animal
     * @param persons array of owner, choose for animals
     * @return random animal
     */
    public Animal createAnimal(int index, Person[] persons) {
        Person owner = persons[random.nextInt(persons.length)];
        int weight = 2000 + random.nextInt(2000);
        return new Animal(index, createName(ANIMAL_NAME), owner, weight);
    }

    /**
     * Create array of Animal
     * @param persons array of potential owners
     * @return array of Animal
     */
    public Animal[] createAnimalArrays(Person[] persons) {
        Animal[] animals = new Animal[ANIMAL_COUNT];
        for (int i = 0; i < ANIMAL_COUNT; i++) {
            animals[i] = createAnimal(i, persons);
        }
        return animals;
    }

    /**
     * Create one random Person
     * @return one new Person
     */
    private Person createPerson() {
        int age = 15 + random.nextInt(60);
        Sex sex;
        if (random.nextInt(2) == 1) {
            sex = Sex.WOMAN;
        } else {
            sex = Sex.MAN;
        }
        String name = createName(PERSON_NAME);
        return new Person(name, age, sex);
    }

    /**
     * Create array of Person
     * @return array of Person
     */
    public Person[] createPersonArrays() {
        Person[] persons = new Person[PERSON_COUNT];
        for (int i = 0; i < PERSON_COUNT; i++) {
            persons[i] = createPerson();
        }
        return persons;
    }

    /**
     * Create randon string
     * @param lengthName count of sign
     * @return random "name"
     */
    private String createName(int lengthName) {
        char[] nameInChar = new char[lengthName];
        nameInChar[0] = UPPER.charAt(random.nextInt(UPPER.length()));
        for (int i = 1; i < lengthName; i++) {
            nameInChar[i] = LOVER.charAt(random.nextInt(LOVER.length()));
        }
        return String.valueOf(nameInChar);
    }
}
