package nc.students.ayaz.model;

import cobertura.IgnoreDuringCodeCoverage;
import nc.students.ayaz.model.exceptions.NoSuchVideoException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {

    private String nickname;

    private List<Video> videos;

    public User(String nickname) {
        this.nickname = nickname;
        videos = new ArrayList<>();
    }

    public String getNickname() {
        return nickname;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public boolean ownsVideo(Video video) {
        return videos.contains(video);
    }

    public Video getVideoByName(String name) throws NoSuchVideoException {
        for (Video video : videos) {
            if (name.equals(video.getName())) {
                return video;
            }
        }

        throw new NoSuchVideoException(name);
    }
}
