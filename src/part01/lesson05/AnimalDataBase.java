package part01.lesson05;

import java.util.*;

/**
 * Animal data base
 * Can find animal, change meta information for animal, add new animal
 * Use HashMap, because never use this collection and want try
 * @param <T> extends Animal
 *
 * @author folkland
 */
public class AnimalDataBase<T extends Animal> {

    private Map<String, T> dataBase;

    public AnimalDataBase(T[] animals) {
        dataBase = new LinkedHashMap<>();
        for (T animal: animals) {
            dataBase.put(animal.getNickname(), animal);
        }
    }

    /**
     * Add to database new animal
     * @param animal which animal must be added
     * @return true - if add success, false - if not
     */
    public boolean addAnimal(T animal) {
        if (!dataBase.containsValue(animal)) {
            dataBase.put(animal.getNickname(), animal);
            return true;
        }
        return false;
    }

    /**
     * Search animal by nickname
     * @param nickname nickname for search
     * @return found animal or null if not find
     */
    public T searchAnimal(String nickname) {
        return dataBase.getOrDefault(nickname, null);
    }

    /**
     * Sorting database and show on console
     */
    public void sortAndShow() {
//        HashMap<String, T> sorted = dataBase.entrySet().stream().sorted().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        HashMap<String, T> sorted = new LinkedHashMap<>();
        dataBase.entrySet().stream().
                sorted(Comparator.comparing(Map.Entry::getValue)).forEachOrdered(e -> sorted.put((String) ((Map.Entry) e).getKey(), (T) ((Map.Entry) e).getValue()));
        Iterator it = sorted.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Show all base to console
     */
    public void showDataBase() {
        Iterator it = dataBase.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Change information about animal
     * @param index which animal must be changed
     * @param weight weight for change
     * @param nickname nickname for change
     */
    public void changeAnimal(int index, int weight, String nickname) {
        String nick = "";
        boolean isNick = false;
        Iterator<Map.Entry<String, T>> it = dataBase.entrySet().iterator();
        while (!isNick && it.hasNext()) {
            Map.Entry<String, T> animal = it.next();
            if (animal.getValue().getIndex() == index) {
                nick = animal.getValue().getNickname();
                isNick = true;
            }
        }
        T animal = dataBase.remove(nick);
        animal.setWeight(weight);
        animal.setNickname(nickname);
        dataBase.put(nickname, animal);
    }
}
