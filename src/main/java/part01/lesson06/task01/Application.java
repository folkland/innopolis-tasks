package part01.lesson06.task01;

import java.io.*;
import java.util.List;

public class Application {

    private static String PATH_TO_DIRECTORY = "resource/lesson06/task01/";
    private static String INPUT_FILE = "input.txt";
    private static String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        String allText = readFromFile();
        System.out.println(allText);
        System.out.println();
        Words wordStore = new Words();
        wordStore.addAllText(allText);
        List<Word> wList = wordStore.getWordStore();
        wordStore.sort(false);
        for (Word str: wList) {
            System.out.println(str);
        }
        writeToFile(wordStore.toString());
    }

    public static String readFromFile() {
        String result = "";
        try (InputStream fis = new FileInputStream(PATH_TO_DIRECTORY + INPUT_FILE)) {
            int c;
            while ((c=fis.read()) != -1) {
                result = result + (char) c;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeToFile(String result) {
        try (OutputStream fos = new FileOutputStream(PATH_TO_DIRECTORY + OUTPUT_FILE)) {
            byte[] bytes = result.getBytes();
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
