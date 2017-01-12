package ru.ayaz;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class VideoTest {

    private Video video;

    @Before
    public final void before() {
        this.video = new Video("FunnyCats", mock(File.class));
    }

    @Test
    public void shouldContainCommentInListWhenAddedToVideo() {
        Comment comment = mock(Comment.class);

        video.addComment(comment);

        assertTrue(video.getComments().contains(comment));
    }

    @Test
    public void shouldNotContainCommentInListWhenRemovedFromVideo() {
        Comment comment = mock(Comment.class);
        video.addComment(comment);

        video.deleteComment(comment);

        assertFalse(video.getComments().contains(comment));
    }
}