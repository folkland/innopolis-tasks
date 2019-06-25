package part01.lesson05;

import java.util.Objects;

/**
 * Class have information about animal: identification number, nickname, owner and weight
 *
 * @author folkland
 */
public class Animal implements Comparable {

    private int index;
    private String nickname;
    private Person owner;
    private int weight;

    public Animal(int index, String nickname, Person owner, int weight) {
        this.index = index;
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getIndex() {
        return index;
    }

    public Person getOwner() {
        return owner;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public int compareTo(Object o) {
        Animal animal = (Animal) o;
        int own = owner.compareTo(animal.getOwner());
        int nick = nickname.compareTo(animal.getNickname());
        if (own > 0) {
            return 1;
        } else if (own == 0 && nick > 0) {
            return 1;
        } else if (own == 0 && nick == 0 && weight < animal.getWeight()) {
            return 1;
        } else if (own == 0 && nick == 0 && weight == animal.getWeight()) {
            return 0;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "index=" + index +
                ", nickname='" + nickname + '\'' +
                ", owner=" + owner +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return index == animal.index &&
                weight == animal.weight &&
                nickname.equals(animal.nickname) &&
                owner.equals(animal.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, nickname, owner, weight);
    }
}
