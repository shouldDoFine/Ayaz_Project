package nc.students.ayaz;

import java.util.ArrayList;
import java.util.List;

class User {

    private List<Video> videos;

    User() {
        videos = new ArrayList<>();
    }

    void addVideo(Video video) {
        videos.add(video);
    }

    boolean ownsVideo(Video video) {
        return videos.contains(video);
    }
}
