package ru.ayaz;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class UserTest {

    @Test
    public void shouldOwnVideoWhenVideoAdded() {
        User user = new User("Ayaz");
        Video video = mock(Video.class);

        user.addVideo(video);

        assertTrue(user.ownsVideo(video));
    }
}