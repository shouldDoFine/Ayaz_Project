package ru.ayaz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommentTest {

    private Comment comment;

    @Before
    public final void before() {
        this.comment = new Comment("Ayaz", "Nice video!");
    }

    @Test
    public void shouldBeEqualWhenAuthorAndTextAreSame() {
        Comment anotherComment = new Comment("Ayaz", "Nice video!");

        assertTrue(comment.equals(anotherComment));
    }

    @Test
    public void shouldNotBeEqualWhenAuthorAndTextAreDifferent() {
        Comment anotherComment = new Comment("Alex", "Not bad");

        assertFalse(comment.equals(anotherComment));
    }
}