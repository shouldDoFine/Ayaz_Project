package nc.students.ayaz.model;

import cobertura.IgnoreDuringCodeCoverage;
import nc.students.ayaz.model.exceptions.NoSuchRecourceException;

import java.util.*;

public class User {

    private String nickname;

    private Map<String, Video> videos;

    public User(String nickname) {
        this.nickname = nickname;
        this.videos = new HashMap<>();
    }

    public String getNickname() {
        return nickname;
    }

    public Map<String, Video> getVideos() {
        return videos;
    }

    public void addVideo(Video video) {
        videos.put(video.getName(), video);
    }

    public boolean ownsVideo(Video video) {
        return videos.containsKey(video.getName());
    }

    public Video getVideoByName(String name) throws NoSuchRecourceException {
        Video video = videos.get(name);

        if(video == null) {
            throw new NoSuchRecourceException();
        }

        return video;
    }

    @Override
    @IgnoreDuringCodeCoverage
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getNickname().equals(user.getNickname());

    }

    @Override
    @IgnoreDuringCodeCoverage
    public int hashCode() {
        return getNickname().hashCode();
    }
}
