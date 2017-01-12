package ru.ayaz;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String nickname;
    private Map<String, Video> userVideos;

    User(String nickname) {
        this.nickname = nickname;
        userVideos = new HashMap<>();
    }

    String getNickname() {
        return nickname;
    }

    void addVideo(Video video) {
        userVideos.put(video.getVideoName(), video);
    }
}
