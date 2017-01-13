package ru.ayaz;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CommentTest {

    @Test
    public void shouldBeEqualWhenReferencesAreSame() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = comment;

        assertTrue(comment.equals(anotherComment));
    }

    @Test
    public void shouldNotBeEqualWhenInstancesAreDifferent() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Object object = mock(Video.class);

        assertFalse(comment.equals(object));
    }

    @Test
    public void shouldNotBeEqualWhenAuthorsAreDifferent() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Alex", "Nice video!");

        assertFalse(comment.equals(anotherComment));
    }

    @Test
    public void shouldBeEqualWhenAuthorAndTextAreSame() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Nice video!");

        assertTrue(comment.equals(anotherComment));
    }

    @Test
    public void shouldBeSameHashCodeWhenAuthorAndTextAreSame() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Nice video!");

        assertEquals(comment.hashCode(), anotherComment.hashCode());
    }
}