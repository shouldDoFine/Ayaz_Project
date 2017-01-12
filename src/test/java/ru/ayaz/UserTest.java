package ru.ayaz;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class UserTest {

    private User user;

    @Before
    public final void before() {
        this.user = new User("Ayaz");
    }

    @Test
    public void shouldContainVideoInListWhenVideoAdded() {
        Video video = new Video("FunnyCats", mock(File.class));
        Video equalVideo = new Video("FunnyCats", mock(File.class));

        user.addVideo(video);

        assertTrue(user.hasVideo(equalVideo));
    }
}