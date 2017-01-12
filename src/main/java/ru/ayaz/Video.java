package ru.ayaz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Video {

    private String videoName;
    private File videoFile;
    private List<Comment> comments;

    Video(String videoName, File videoFile) {
        this.videoName = videoName;
        this.videoFile = videoFile;
        comments = new ArrayList<>();
    }

    String getVideoName() {
        return videoName;
    }

    File getVideoFile() {
        return videoFile;
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
