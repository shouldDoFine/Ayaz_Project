package nc.students.ayaz.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CommentTest {

    @Test
    public void shouldNotBeEqualWhenAuthorsAreDifferent() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Alex", "Nice video!");

        assertNotEquals(comment, anotherComment);
    }

    @Test
    public void shouldNotBeEqualWhenAuthorsAreSameAndTextsDifferent() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Sad video!");

        assertNotEquals(comment, anotherComment);
    }

    @Test
    public void shouldBeEqualWhenAuthorAndTextAreSame() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Nice video!");

        assertEquals(comment, anotherComment);
    }

    @Test
    public void shouldHaveSameHashCodeWhenAuthorAndTextAreSame() {
        Comment comment = new Comment("Ayaz", "Nice video!");
        Comment anotherComment = new Comment("Ayaz", "Nice video!");

        assertEquals(comment.hashCode(), anotherComment.hashCode());
    }

    @Test
    public void shouldGetAuthorWhenCommentCreated() {
        Comment comment = new Comment("Ayaz", "Nice video!");

        assertEquals("Ayaz", comment.getAuthor());
    }

    @Test
    public void shouldGetTextWhenCommentCreated() {
        Comment comment = new Comment("Ayaz", "Nice video!");

        assertEquals("Nice video!", comment.getText());
    }
}