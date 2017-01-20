package nc.students.ayaz.model.exceptions;

public class NoSuchVideoException extends Exception {

    private String name;

    public NoSuchVideoException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
