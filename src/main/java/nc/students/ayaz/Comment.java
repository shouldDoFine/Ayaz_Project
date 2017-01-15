package nc.students.ayaz;

import java.util.Calendar;
import java.util.Date;

class Comment {

    private String author;
    private String text;

    Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return author.equals(comment.author) && text.equals(comment.text);
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
