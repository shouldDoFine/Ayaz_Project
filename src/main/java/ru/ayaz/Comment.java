package ru.ayaz;

import java.util.Calendar;
import java.util.Date;

public class Comment {

    private String author;
    private String text;
    private Date date;

    Comment(String author, String text) {
        this.author = author;
        this.text = text;
        this.date = Calendar.getInstance().getTime();
    }

    String getAuthor() {
        return author;
    }

    String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        Comment comment = (Comment) o;
        if (author.equals(comment.getAuthor())) {
            if (text.equals(comment.getText())) {
                return true;
            }
        }
        return false;
    }
}
