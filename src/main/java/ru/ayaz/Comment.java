package ru.ayaz;

import java.util.Calendar;
import java.util.Date;

public class Comment {

    private String text;
    private Date date;

    Comment(String text) {
        this.text = text;
        this.date = Calendar.getInstance().getTime();
    }

    String getText() {
        return text;
    }

    Date getDate() {
        return date;
    }
}
