package nc.students.ayaz.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDeserializerTest {

    @Test
    public void shouldDeserializetWhenGivenUserJsonString() throws IOException {
        UserDeserializer deserializer = new UserDeserializer();
        ObjectMapper mapper = new ObjectMapper();
        //String jsonString = "[{\"name\":\"FunnyCats\",\"comments\":[{\"author\":\"Alex\",\"text\":\"I like it!\"}]}]";
        String jsonString = "{\"nickname\":\"Ayaz\",\"videos\":[{\"name\":\"FunnyCats\"}]}";
        JsonParser parser = mapper.getFactory().createParser(jsonString);
        DeserializationContext context = mapper.getDeserializationContext();

        User user = deserializer.deserialize(parser, context);

        assertEquals("Ayaz", user.getNickname());
        List<Video> videos = user.getVideos();
        assertTrue(videos.contains(new Video("FunnyCats")));
    }
}