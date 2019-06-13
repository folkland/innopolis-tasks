package part01.lesson06.task02;

import java.security.SecureRandom;

/**
 * class for create sentence and some other methods if will need
 */
public class Sentence {

    private SecureRandom random = new SecureRandom();
    private String[] sentence;
    private static final int MAX_LENGTH = 15;
    private static final int MAX_WORD_SIZE = 15;

    public Sentence(String[] words, int chance, int size) {
        int length = random.nextInt(MAX_LENGTH) + 1;
        if (size > 2) {
            sentence = createSentence(length, words, chance, size);
        } else {
            sentence = new String[0];
        }
    }

    /**
     * Create random sentence, use Word class
     * @param sentenceLength length of sentence
     * @return sentence
     */
    private String[] createSentence(int sentenceLength, String[] words, int chance, int size) {
        int wSize = MAX_WORD_SIZE;
        if (size < MAX_WORD_SIZE - 2) {
            wSize = size - 2;
        }
        int tempSize = 0;
        String[] sentence = new String[sentenceLength];
        boolean isChance = false;
        int indexChance = -1;
        if (random.nextInt(101) < chance) {
            isChance = true;
            indexChance = random.nextInt(sentenceLength);
        }
        sentence[0] = new Word(true, wSize).toString();
        tempSize = tempSize + sentence[0].length() + 2;
        for (int i = 1; i < sentenceLength && tempSize < size - 2; i++) {
            if (size - tempSize < MAX_WORD_SIZE - 1 && size > tempSize) {
                wSize = size - tempSize - 2;
            }
            if (isChance) {
                if (i == indexChance) {
                    sentence[i] = words[random.nextInt(words.length)];
                    tempSize = tempSize + sentence[i].length() + 2;
                    System.out.println("Used probability chance");
                    continue;
                }
            }
            sentence[i] = new Word(false, wSize).toString();
            tempSize = tempSize + sentence[i].length() + 2;
        }
        return sentence;
    }

    @Override
    public String toString() {
        String result = "";
        int length = sentence.length;
        for (int i = 0; i < sentence.length; i++) {
            if (sentence[i] == null && length == sentence.length) {
                length = i;
            }
        }
        for (int i = 0; i < length; i++) {
            result = result + sentence[i];
            if (i + 1 != length && random.nextInt(10) == 3) {
                result = result + ',';
            }
            if (i + 1 == length) {
                int k = random.nextInt(5);
                switch (k) {
                    case 1: result = result + '?'; break;
                    case 2: result = result + '!'; break;
                    default: result = result + '.'; break;
                }
            }
            result = result + ' ';
        }
        return result;
    }
}
