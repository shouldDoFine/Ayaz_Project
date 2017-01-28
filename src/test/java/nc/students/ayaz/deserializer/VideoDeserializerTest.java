package nc.students.ayaz.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import nc.students.ayaz.model.Comment;
import nc.students.ayaz.model.Video;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VideoDeserializerTest {

    @Test
    public void shouldDeserializeWhenGivenVideoJsonString() throws IOException {
        VideoDeserializer deserializer = new VideoDeserializer();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"FunnyCats\",\"comments\":[{\"author\":\"Alex\",\"text\":\"I like it!\"}]}";
        JsonParser parser = mapper.getFactory().createParser(jsonString);
        DeserializationContext context = mapper.getDeserializationContext();

        Video video = deserializer.deserialize(parser, context);

        assertEquals("FunnyCats", video.getName());
        List<Comment> comments = video.getComments();
        assertTrue(comments.contains(new Comment("Alex", "I like it!")));
    }
}