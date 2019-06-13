package part01.lesson06.task01;

import java.util.Locale;
import java.util.Objects;

/**
 * class need for sorting and compare word
 *
 * @author folkland
 */
public class Word implements Comparable{

    private String word;
    private String loverWord;

    public Word(String word) {
        this.word = word;
        this.loverWord = word.toLowerCase(Locale.ROOT);
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(Object o) {
        Word w = (Word) o;
        return loverWord.compareTo(w.loverWord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return loverWord.equals(word1.loverWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
