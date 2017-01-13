package ru.ayaz;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class VideoTest {

    @Test
    public void shouldContainCommentInListWhenCommentAdded() {
        Video video = new Video("FunnyCats", mock(File.class));
        Comment comment = mock(Comment.class);

        video.addComment(comment);

        assertTrue(video.hasComment(comment));
    }

    @Test
    public void shouldNotContainCommentInListWhenRemovedFromVideo() {
        Video video = new Video("FunnyCats", mock(File.class));
        Comment comment = mock(Comment.class);
        video.addComment(comment);

        video.deleteComment(comment);

        assertFalse(video.hasComment(comment));
    }

    @Test
    public void shouldBeEqualWhenReferencesAreSame() {
        Video video = new Video("FunnyCats", mock(File.class));
        Video anotherVideo = video;

        assertTrue(video.equals(anotherVideo));
    }

    @Test
    public void shouldNotBeEqualWhenInstancesAreDifferent() {
        Video video = new Video("FunnyCats", mock(File.class));
        Object object = mock(Comment.class);

        assertFalse(video.equals(object));
    }

    @Test
    public void shouldBeEqualWhenNamesAreSame() {
        Video video = new Video("FunnyCats", mock(File.class));
        Video anotherVideo = new Video("FunnyCats", mock(File.class));

        assertTrue(video.equals(anotherVideo));
    }

    @Test
    public void shouldBeSameHashCodeWhenNamesAreSame() {
        Video video = new Video("FunnyCats", mock(File.class));
        Video anotherVideo = new Video("FunnyCats", mock(File.class));

        assertEquals(video.hashCode(), anotherVideo.hashCode());
    }
}