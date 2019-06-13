package part01.lesson02.task03.sorting;


/**
 * Хочу подсчитать время выполнения алгоритмов
 * для этого и добавляю этот абстрактный класс
 * возможно мой подход и не верный
 *
 * @author folkland
 */
public abstract class SortingAbstractClass implements SortingInterface {
    private long time = -1;

    @Override
    public long time() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
