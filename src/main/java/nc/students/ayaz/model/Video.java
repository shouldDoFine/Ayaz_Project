package nc.students.ayaz.model;

import cobertura.IgnoreDuringCodeCoverage;

import java.util.ArrayList;
import java.util.List;

public class Video {

    private final String name;

    private final List<Comment> comments;

    public Video(String videoName) {
        this.name = videoName;
        this.comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    boolean hasComment(Comment comment) {
        return comments.contains(comment);
    }

    @Override
    @IgnoreDuringCodeCoverage
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        return name.equals(video.name);
    }

    @Override
    @IgnoreDuringCodeCoverage
    public int hashCode() {
        return name.hashCode();
    }
}
