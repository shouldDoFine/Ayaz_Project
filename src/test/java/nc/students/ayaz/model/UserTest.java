package nc.students.ayaz.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {

    @Test
    public void shouldOwnVideoWhenVideoAdded() {
        User user = new User("Ayaz");
        Video video = mock(Video.class);
        when(video.getName()).thenReturn("FunnyCats");

        user.addVideo(video);

        assertTrue(user.ownsVideo("FunnyCats"));
    }

    @Test
    public void shouldBeAbleToGetVideoByNameWhenItWasAddedEarlier() throws Exception {
        User user = new User("Ayaz");
        Video video = mock(Video.class);
        when(video.getName()).thenReturn("FunnyCats");
        user.addVideo(video);

        Video fetchedVideo = user.getVideoByName("FunnyCats");

        assertEquals(video, fetchedVideo);
    }

    @Test
    public void shouldBeEqualWhenNamesAreSame() {
        User user = new User("Ayaz");
        User anotherUser = new User("Ayaz");

        assertEquals(user, anotherUser);
    }

    @Test
    public void shouldHaveSameHashCodeWhenNamesAreSame() {
        User user = new User("Ayaz");
        User anotherUser = new User("Ayaz");

        assertEquals(user.hashCode(), anotherUser.hashCode());
    }
}