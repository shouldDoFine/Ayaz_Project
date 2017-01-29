package nc.students.ayaz.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import nc.students.ayaz.model.User;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDeserializerTest {

    @Test
    public void shouldDeserializetWhenGivenUserJsonString() throws IOException {
        UserDeserializer deserializer = new UserDeserializer();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"nickname\":\"Ayaz\",\"videos\":[{\"name\":\"FunnyCats\"}]}";
        JsonParser parser = mapper.getFactory().createParser(jsonString);
        DeserializationContext context = mapper.getDeserializationContext();

        User user = deserializer.deserialize(parser, context);

        assertEquals("Ayaz", user.getNickname());
        assertTrue(user.ownsVideo("FunnyCats"));
    }
}