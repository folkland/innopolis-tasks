package part01.lesson05;

/**
 * Application run class
 *
 * @author folkland
 */
public class Application {

    public static void main(String[] args) {
        GenerateAnimalAndPerson gap = new GenerateAnimalAndPerson(7, 10);
        Person[] persons = gap.createPersonArrays();
        Animal[] animals = gap.createAnimalArrays(persons);
        System.out.println();
        AnimalDataBase<Animal> base = new AnimalDataBase<>(animals);
        base.showDataBase();
        System.out.println();
        base.addAnimal(new Animal(10, "Fred", persons[0], 150));
        base.changeAnimal(10, 560, "Freddy");
        base.showDataBase();
        System.out.println();
        System.out.println(base.searchAnimal("Freddy"));
        System.out.println(base.searchAnimal("Frddy"));
        System.out.println();
        base.sortAndShow();
    }
}
