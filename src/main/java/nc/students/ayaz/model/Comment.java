package nc.students.ayaz.model;

import cobertura.IgnoreDuringCodeCoverage;

public class Comment {

    private final String author;

    private final String text;

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    @Override
    @IgnoreDuringCodeCoverage
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return author.equals(comment.author) && text.equals(comment.text);
    }

    @Override
    @IgnoreDuringCodeCoverage
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
