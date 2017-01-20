package nc.students.ayaz.model;

import nc.students.ayaz.model.Comment;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommentTest {

    @Test
    public void shouldNotBeEqualWhenAuthorsAreDifferent() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Alex", "Nice video!");

        assertFalse(comment.equals(anotherComment));
    }

    @Test
    public void shouldNotBeEqualWhenAuthorsAreSameAndTextsDifferent() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Sad video!");

        assertFalse(comment.equals(anotherComment));
    }

    @Test
    public void shouldBeEqualWhenAuthorAndTextAreSame() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Nice video!");

        assertTrue(comment.equals(anotherComment));
    }

    @Test
    public void shouldHaveSameHashCodeWhenAuthorAndTextAreSame() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Nice video!");

        assertEquals(comment.hashCode(), anotherComment.hashCode());
    }
}