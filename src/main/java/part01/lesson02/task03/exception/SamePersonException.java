package part01.lesson02.task03.exception;

public class SamePersonException extends IllegalArgumentException {

    public  SamePersonException() {
        super();
    }
    public SamePersonException(String text) {
        super(text);
    }
}
