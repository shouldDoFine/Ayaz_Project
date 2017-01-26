package nc.students.ayaz.model;

import cobertura.IgnoreDuringCodeCoverage;
import nc.students.ayaz.model.exceptions.NoSuchVideoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class User {

    private String nickname;

    private final Map<String, Video> videos;

    public User(String nickname) {
        this.nickname = nickname;
        this.videos = new HashMap<>();
    }

    public String getNickname() {
        return nickname;
    }

    public List<Video> getVideos() {
        return new ArrayList<Video>(videos.values());
    }

    public void addVideo(Video video) {
        videos.put(video.getName(), video);
    }

    public boolean ownsVideo(String name) {
        return videos.containsKey(name);
    }

    public Video getVideoByName(String name) throws NoSuchVideoException {
        if (videos.containsKey(name)) {
            return videos.get(name);
        } else {
            throw new NoSuchVideoException();
        }
    }

    @Override
    @IgnoreDuringCodeCoverage
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return nickname.equals(user.nickname);

    }

    @Override
    @IgnoreDuringCodeCoverage
    public int hashCode() {
        return nickname.hashCode();
    }
}
