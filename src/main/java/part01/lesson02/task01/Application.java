package part01.lesson02.task01;

public class Application {

    public static void main(String[] args) {
        try {
            Integer nullException = null;
            nullException++;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            int[] array = new int[15];
            int ls = array[20];
            System.out.println(ls);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        try {
            int x = 0;
            int i = 5 / x;
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Value of x is zero");
        }
    }
}
