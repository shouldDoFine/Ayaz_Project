package nc.students.ayaz.model;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class VideoTest {

    @Test
    public void shouldHaveCommentWhenCommentAdded() {
        Video video = new Video("FunnyCats");
        Comment comment = mock(Comment.class);

        video.addComment(comment);

        assertTrue(video.hasComment(comment));
    }

    @Test
    public void shouldNotHaveCommentWhenRemovedFromVideo() {
        Video video = new Video("FunnyCats");
        Comment comment = mock(Comment.class);
        video.addComment(comment);

        video.deleteComment(comment);

        assertFalse(video.hasComment(comment));
    }

    @Test
    public void shouldBeEqualWhenNamesAreSame() {
        Video video = new Video("FunnyCats");
        Video anotherVideo = new Video("FunnyCats");

        assertEquals(video, anotherVideo);
    }

    @Test
    public void shouldHaveSameHashCodeWhenNamesAreSame() {
        Video video = new Video("FunnyCats");
        Video anotherVideo = new Video("FunnyCats");

        assertEquals(video.hashCode(), anotherVideo.hashCode());
    }
}