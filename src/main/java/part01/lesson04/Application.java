package part01.lesson04;

public class Application {

    private static final int COUNT = 10;

    public static void main(String[] args) {
        CreateArray<Integer> createIntArray = new CreateArray<>(Integer.class);
        Number[] intArray = createIntArray.createArray(COUNT);
        MathBox<Number> mathIntBox = new MathBox<>(intArray);
        System.out.println("Операции над Integer массивом");
        System.out.println(mathIntBox.dump());
        System.out.println("Сумма всех: " + mathIntBox.summator());
        Number deleteIntElement = mathIntBox.getValue(0);
        mathIntBox.checkInteger(deleteIntElement);
        System.out.println("После попытки удаления первого элемента");
        System.out.println(mathIntBox.toString());
        mathIntBox.splitter(3);
        System.out.println("После деления на 3");
        System.out.println(mathIntBox.dump());

        System.out.println();

        CreateArray<Double> createDoubleArray = new CreateArray<>(Double.class);
        Number[] doubleArray = createDoubleArray.createArray(COUNT);
        MathBox<Number> mathDoubleBox = new MathBox<>(doubleArray);
        System.out.println("Операции над Double массивом");
        System.out.println(mathDoubleBox.dump());
        System.out.println("Сумма всех: " + mathDoubleBox.summator());
        Number deleteDoubleElement = mathDoubleBox.getValue(0);
        mathDoubleBox.checkInteger(deleteDoubleElement);
        System.out.println("После попытки удаления первого элемента");
        System.out.println(mathDoubleBox.toString());
        mathDoubleBox.splitter(3);
        System.out.println("После деления на 3");
        System.out.println(mathDoubleBox.dump());

        System.out.println();

        CreateArray<Float> createFloatArray = new CreateArray<>(Float.class);
        Number[] floatArray = createFloatArray.createArray(COUNT);
        MathBox<Number> mathFloatBox = new MathBox<>(floatArray);
        System.out.println("Операции над Float массивом");
        System.out.println(mathFloatBox.dump());
        System.out.println("Сумма всех: " + mathFloatBox.summator());
        Number deleteFloatElement = mathFloatBox.getValue(0);
        mathFloatBox.checkInteger(deleteFloatElement);
        System.out.println("После попытки удаления первого элемента");
        System.out.println(mathFloatBox.toString());
        mathFloatBox.splitter(3);
        System.out.println("После деления на 3");
        System.out.println(mathFloatBox.dump());
    }
}
