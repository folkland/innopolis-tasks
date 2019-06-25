package part01.lesson06.task02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Application {

    private static final String PATH = "resource/lesson06/task02/";

    private static final int MAX_PARAGRAPH_SIZE = 5102;
    private static final int MAX_ADDITIONAL_WORDS = 1000;

    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        String[] words = getWords();
        getFiles(PATH, 4, 6000, words, 5);
    }

    private static void getFiles(String path, int n, int size, String[] words, int probability) {
        Double c = 1.0 / probability * 100;
        int chance = c.intValue();
        for (int i = 0; i < n; i++) {
            String data = "";
            int tempSize = 0;
            int pSize = MAX_PARAGRAPH_SIZE;
            if (size < MAX_PARAGRAPH_SIZE) {
                pSize = size;
            }
            while (tempSize < size) {
                if (size - tempSize < MAX_PARAGRAPH_SIZE) {
                    pSize = size - tempSize;
                }
                String paragraph = new Paragraph(words, chance, pSize).toString();
                tempSize = tempSize + paragraph.length();
                data = data + paragraph;
            }
            System.out.println(data.length());
            String fileName = path + "file-" + i + ".txt";
            try {
                Files.write(Paths.get(fileName), data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String[] getWords() {
        String[] words = new String[random.nextInt(MAX_ADDITIONAL_WORDS) + 1];
        for (int i = 0; i < words.length; i++) {
            words[i] = new Word(false, 15).toString();
        }
        return words;
    }
}
