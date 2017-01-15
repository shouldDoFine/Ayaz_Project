package nc.students.ayaz;

import java.util.Calendar;
import java.util.Date;

class Comment {

    private String author;
    private String text;
    private Date date;

    Comment(String author, String text) {
        this.author = author;
        this.text = text;
        this.date = Calendar.getInstance().getTime();
    }

    private String getAuthor() {
        return author;
    }

    private String getText() {
        return text;
    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        Comment comment = (Comment) o;

        if (!getAuthor().equals(comment.getAuthor())) {
            return false;
        }

        return getText().equals(comment.getText());
    }
}
