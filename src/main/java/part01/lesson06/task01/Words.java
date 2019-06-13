package part01.lesson06.task01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Store all words from file, methods: add word, sort, getAll
 *
 * @author folkland
 */
public class Words {

    private List<Word> wordStore;

    public Words() {
        wordStore = new ArrayList<>();
    }

    /**
     * Add word to list of words
     * @param word
     */
    public void addWord(String word) {
        Word w = new Word(word);
        if (!wordStore.contains(w)) {
            wordStore.add(new Word(word));
        }
    }

    /**
     * Add words array
     * @param words words which must be added
     */
    public void addWords(String[] words) {
        for (String w: words) {
            addWord(w);
        }
    }

    /**
     * Add each word from some text
     * @param allText text with words
     */
    public void addAllText(String allText) {
        String[] lines = allText.split("\r\n");
        for (String line: lines) {
            addWords(line.split(" "));
        }
    }

    /**
     * Get all words
     * @return store of words
     */
    public List<Word> getWordStore() {
        return wordStore;
    }

    /**
     * Sort list
     * @param reverse true - decs, false - asc
     */
    public void sort(boolean reverse) {
        if (reverse) {
            Collections.sort(wordStore, Collections.reverseOrder());
        } else {
            Collections.sort(wordStore);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (Word word: wordStore) {
            result = result + word.getWord() + "\n";
        }
        return result;
    }
}
