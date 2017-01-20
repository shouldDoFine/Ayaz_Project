package nc.students.ayaz.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {

    @Test
    public void shouldOwnVideoWhenVideoAdded() {
        User user = new User("Ayaz");
        Video video = mock(Video.class);

        user.addVideo(video);

        assertTrue(user.ownsVideo(video));
    }

    @Test
    public void shouldGetVideoWhenPassedVideoName() throws Exception {
        User user = new User("Ayaz");
        Video expectedVideo = mock(Video.class);
        when(expectedVideo.getName()).thenReturn("FunnyCats");
        user.addVideo(expectedVideo);

        Video video = user.getVideoByName("FunnyCats");

        assertTrue(expectedVideo.equals(video));

    }
}