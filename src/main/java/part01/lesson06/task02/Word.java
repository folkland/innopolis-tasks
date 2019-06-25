package part01.lesson06.task02;

import java.security.SecureRandom;
import java.util.Locale;

/**
 * class for create word and some other methods if will need
 */
public class Word {

    private SecureRandom random = new SecureRandom();
    private String word;

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOVER = UPPER.toLowerCase(Locale.ROOT);
    private static final int MAX_LENGTH = 15;

    public Word(boolean upper, int size) {
        int length = random.nextInt(MAX_LENGTH) + 1;
        if (size < MAX_LENGTH) {
            length = size;
        }
        this.word = createWord(length, upper);
    }

    @Override
    public String toString() {
        return word;
    }

    /**
     * Create random word
     * @param wordLength length of word
     * @param upper true - first sign upper, false - all sign lover
     * @return word
     */
    public String createWord(int wordLength, boolean upper) {
        char[] word = new char[wordLength];
        int start = 0;
        if (upper && wordLength > 0) {
            word[start] = UPPER.charAt(random.nextInt(UPPER.length()));
            start++;
        }
        for (int i = start; i < wordLength; i++) {
            word[i] = LOVER.charAt(random.nextInt(LOVER.length()));
        }
        return String.valueOf(word);
    }
}
