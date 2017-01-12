package ru.ayaz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Video {

    private String name;
    private File file;
    private List<Comment> comments;

    Video(String videoName, File videoFile) {
        this.name = videoName;
        this.file = videoFile;
        comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        Video video = (Video) o;
        if (name.equals(video.getName())) {
            return true;
        }
        return false;
    }

    boolean containsComment(Comment comment) {
        return comments.contains(comment);
    }

    void addComment(Comment comment) {
        comments.add(comment);
    }

    void deleteComment(Comment comment) {
        comments.remove(comment);
    }
}
