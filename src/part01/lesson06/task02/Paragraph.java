package part01.lesson06.task02;

import java.security.SecureRandom;
import java.util.Arrays;

public class Paragraph {

    private SecureRandom random = new SecureRandom();
    private String[] paragraph;
    private final int MAX_LENGTH = 20;
    private final int MAX_SENTENCE_SIZE = 255;

    public Paragraph(String[] words, int chance, int size) {
        int length = random.nextInt(MAX_LENGTH) + 1;
        paragraph = createParagraph(length, words, chance, size);
    }

    /**
     * Create random paragraph, use Sentence class
     * @param paragraphLength length of paragraph
     * @return paragraph
     */
    private String[] createParagraph(int paragraphLength, String[] words, int chance, int size) {
        int sSize = MAX_SENTENCE_SIZE;
        if (size < MAX_SENTENCE_SIZE - 1) {
            sSize = size - 1;
        }
        int tempSize = 0;
        String[] paragraph = new String[paragraphLength];
        for (int i = 0; i < paragraphLength && tempSize < size - 1; i++) {
            if (size - tempSize < MAX_SENTENCE_SIZE) {
                sSize = size - tempSize - 1;
            }
            paragraph[i] = new Sentence(words, chance, sSize).toString();
            tempSize = tempSize + paragraph[i].length() + 1;
        }
        return paragraph;
    }

    @Override
    public String toString() {
        String result = "";
        for (String sentence: paragraph) {
            if (sentence == null) {
                continue;
            }
            result = result + sentence;
        }
        result = result + "\n";
        return result;
    }
}
