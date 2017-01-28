package nc.students.ayaz.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import nc.students.ayaz.model.User;
import nc.students.ayaz.model.Video;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        User user = new User(node.get("nickname").asText());

        JsonNode arrayNode = node.get("videos");
        for (JsonNode arrayNodeObject : arrayNode) {
            user.addVideo(new Video(arrayNodeObject.get("name").asText()));
        }

        return user;
    }
}
