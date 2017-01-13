package ru.ayaz;

import java.util.ArrayList;
import java.util.List;

class User {

    private String nickname;
    private List<Video> videos;

    User(String nickname) {
        this.nickname = nickname;
        videos = new ArrayList<>();
    }

    void addVideo(Video video) {
        videos.add(video);
    }

    boolean hasVideo(Video video) {
        return videos.contains(video);
    }
}
