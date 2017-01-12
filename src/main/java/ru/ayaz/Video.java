package ru.ayaz;

import java.util.ArrayList;
import java.util.List;

class Video {

    private String videoName;
    private List<Comment> comments;

    Video(String videoName) {
        this.videoName = videoName;
        comments = new ArrayList<>();
    }

    String getVideoName() {
        return videoName;
    }


    List<Comment> getComments() {
        return comments;
    }

    void addComment(Comment comment) {
        comments.add(comment);
    }

    void deleteComment(Comment comment) {
        comments.remove(comment);
    }
}
