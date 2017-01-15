package nc.students.ayaz;

import java.util.ArrayList;
import java.util.List;

class Video {

    private String name;
    private List<Comment> comments;

    Video(String videoName) {
        this.name = videoName;
        comments = new ArrayList<>();
    }

    boolean hasComment(Comment comment) {
        return comments.contains(comment);
    }

    void addComment(Comment comment) {
        comments.add(comment);
    }

    void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Video video = (Video) o;

        return name.equals(video.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
