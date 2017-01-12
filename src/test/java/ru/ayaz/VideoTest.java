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
    public void shouldContainCommentInListWhenCommentAdded() {
        Comment comment = new Comment("Ayaz", "Hm, nice video!");
        Comment equalComment = new Comment("Ayaz", "Hm, nice video!");

        video.addComment(comment);

        assertTrue(video.containsComment(equalComment));
    }

    @Test
    public void shouldNotContainCommentInListWhenRemovedFromVideo() {
        Comment comment = new Comment("Ayaz", "Hm, nice video!");
        Comment equalComment = new Comment("Ayaz", "Hm, nice video!");
        video.addComment(comment);

        video.deleteComment(equalComment);

        assertFalse(video.containsComment(comment));
    }

    @Test
    public void shouldBeEqualWhenNamesAreSame() {
        Video anotherVideo = new Video("FunnyCats", mock(File.class));

        assertTrue(video.equals(anotherVideo));
    }

    @Test
    public void shouldNotBeEqualWhenNameAreDifferent() {
        Video anotherVideo = new Video("SadPuppies", mock(File.class));

        assertFalse(video.equals(anotherVideo));
    }
}